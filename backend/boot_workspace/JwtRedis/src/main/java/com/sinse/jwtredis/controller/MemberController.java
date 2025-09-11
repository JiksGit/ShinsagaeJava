package com.sinse.jwtredis.controller;

import com.sinse.jwtredis.Domain.CustomUserDetails;
import com.sinse.jwtredis.Domain.Member;
import com.sinse.jwtredis.controller.dto.LogoutRequest;
import com.sinse.jwtredis.controller.dto.MemberDTO;
import com.sinse.jwtredis.controller.dto.TokenResponse;
import com.sinse.jwtredis.model.member.MemberService;
import com.sinse.jwtredis.model.member.RedisTokenService;
import com.sinse.jwtredis.model.member.RegistService;
import com.sinse.jwtredis.util.CookieUtil;
import com.sinse.jwtredis.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@Slf4j
@RestController
public class MemberController {

    private AuthenticationManager authenticationManager;
    private RegistService registService;
    private final MemberService memberService;

    // JWT 관련
    private final JwtUtil jwtUtil;
    private final RedisTokenService redisTokenService;
    private final long accessMinutes;
    private final long refreshDays;

    public MemberController(JwtUtil jwtUtil, RedisTokenService redisTokenService, RegistService registService, MemberService memberService, AuthenticationManager authenticationManager
            , @Value("${app.jwt.access-minutes}") long accessMinutes
            , @Value("${app.jwt.refresh-days}") long refreshDays
    ) {
        this.jwtUtil = jwtUtil;
        this.redisTokenService = redisTokenService;
        this.registService = registService;
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
        this.accessMinutes = accessMinutes;
        this.refreshDays = refreshDays;
    }

    @PostMapping("/member/regist")
    public ResponseEntity<?> regist(@RequestBody MemberDTO memberDTO) {
        log.debug("회원가입을 위해 사용자가 넣은 파라미터는 : {}", memberDTO);

        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPwd());

        memberService.regist(member);
//        registService.regist(member);

        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    // 로그인 요청 처리
    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO, HttpServletResponse response) {

        log.debug("개발자 정의 컨트롤러 로그인 요청 받음");

        // 유효한 JWT를 보유했는지 여부를 먼저 따져보자
        // 따라서 db에 회원이 존재하는지 여부를 판단
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPwd());

        // 인증 시도
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        log.debug("인증받은 회원의 아이디는 " + userDetails.getUsername());
        log.debug("인증받은 회원의 이메일은 " + userDetails.getEmail());
        log.debug("인증받은 회원의 권한은 " + userDetails.getRoleName());

        // 인증에 성공하면 AccessToken(값) != RefreshToken(값) - 재발급의 대상이 되는 지 검증

        // 사용자 전역(모든 디바이스를 섭렵하므로) 토큰 버전 가져오기
        int userVersion = redisTokenService.currentUserVersion(userDetails.getUsername());

        // 토큰 발급
        // 참고) 원래 디바이스 아이디는 디바이스마다 고유해야 하므로, UUID를 적극 활용하자
        String accessToken = jwtUtil.createAccessToken(userDetails.getUsername(), userVersion, memberDTO.getDeviceId());
        String refreshToken = jwtUtil.createRefreshToken(userDetails.getUsername(), memberDTO.getDeviceId());

        long rfTtlSec = refreshDays * 24 * 60 * 60;

        // refresh 토큰의 경우, 서버에 저장해놓아야, 추후 재발급시 클라이언트가 전송한 쿠키에 들어있는
        // refreshToken과 비교가 가능하므로, redis에 저장하자
        redisTokenService.saveRefreshToken(userDetails.getUsername(), memberDTO.getDeviceId(), refreshToken, rfTtlSec);

        // RefreshToken을 보안 쿠키에 담기
        CookieUtil.setRefreshCookie(response, refreshToken, (int) rfTtlSec);

        // AccessToken의 유효시간
        long expSec = jwtUtil.parseToken(accessToken).getBody().getExpiration().toInstant().getEpochSecond();

        return ResponseEntity.ok(new TokenResponse(accessToken, expSec));
    }



    @PostMapping("/member/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "Refresh", required = false) String rtCookie
            , @RequestBody MemberDTO memberDTO, HttpServletResponse response) {
        try {
            // 쿠키가 없다면 401 error 보내기
            if(!StringUtils.hasText(rtCookie)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error","no refresh cookie"));
            }

            // 필수는 아니지만, 한 명의 유저가 보유한 여러 디바이스와 관련 인증 처리할 경우 deviceId
            // 파라미터가 dto가 아니므로 별도 처리 불필요

            // 재발급에 앞서, RefreshToken이 유효한 지를 검증하자
            Jws<Claims> jws = jwtUtil.parseToken(rtCookie);
            Claims claims = jws.getBody();
            String userId = claims.getSubject(); // userId
            log.debug("Controller deviceId : {} " + memberDTO.getDeviceId());

            // redis와 일치 여부를 판단
            if(!redisTokenService.matchesRefreshToken(userId, memberDTO.getDeviceId(), rtCookie)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error","refresh not matched"));
            }

            // redis에서 관리중인 userVersion을 가져오기
            // 현재 사용자 버전 겨자오기
            int version = redisTokenService.currentUserVersion(userId);
            // 보안상 안정성을 위해 AccessToken만 발급하지 말고, RefreshToken 조차 갱신하는게 좋다
            String newAccessToken = jwtUtil.createAccessToken(userId, version, memberDTO.getDeviceId());
            String newRefreshToken = jwtUtil.createRefreshToken(userId, memberDTO.getDeviceId());

            // RefreshToken 새롭게 발급되었으므로 기존 redis가 보관하고 있던 refreshToken을 제거하고
            // 새롭게 다시 넣자!
            redisTokenService.deleteRefreshToken(userId, memberDTO.getDeviceId());
            long rtTtlSec = refreshDays * 24 * 60 * 60;
            redisTokenService.saveRefreshToken(userId, memberDTO.getDeviceId(), newRefreshToken, rtTtlSec);

            // 보안 처리된 쿠키에 refreshToken 담기
            CookieUtil.setRefreshCookie(response, newRefreshToken, (int) rtTtlSec);

            // 원래 목적이었던 AccessToken을 응답 body에 넣기
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error","refresh failed"));
        }
    }

    // 회원 정보 요청 처리
    @GetMapping("/member/myinfo")
    public ResponseEntity<?> myinfo(HttpServletResponse response) {
        return ResponseEntity.ok("당신은 인증받은 회원");
    }

    /*  로그아웃 요청 처리
        1) 로그아웃을 요청하는 클라이언트의 AccessToken을 블랙리스트로 등록
        2) 회원으로써 서비스 이용을 중단 요청이기에 Redis에 등록된 RefreshToken 삭제
        3) 쿠키에 들어있는 refreshToken 삭제
     */
    @PostMapping("/member/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request, HttpServletResponse response) {
        try {
            // 1) 요청의 유효성을 판단하여, 유효하던 유효하지 않던간에 무조건 로그아웃에 대한 요청 처리
            // 결과는 동일한 '성공'으로 응답하자
            if (request == null || !StringUtils.hasText(request.getAccessToken())
                    || !StringUtils.hasText(request.getDeviceId())) {
                return ResponseEntity.ok(Map.of("result", "로그아웃 성공"));
            }

            // 클라이언트가 전송한 AccessToken에서 정보꺼내기
            Jws<Claims> jws = jwtUtil.parseToken(request.getAccessToken());
            Claims claims =  jws.getBody();
            String userId = claims.getSubject();
            String jti = claims.getId();

            // 현재 시간과 JWT가 보유한 유효기간을 구해서 남은 TTL을 구하자
            long exp = claims.getExpiration().toInstant().getEpochSecond();
            long now = Instant.now().getEpochSecond();
            // 결과가 양수라면 - 만료까지 남은 초
            // 결과가 음수라면 - 남은 시간이 없음, 즉 만료..
            long ttl = Math.max(0, exp - now); // 남은 만료 초

            // redis 블랙리스트에 등록
            redisTokenService.registBlackList(jti, ttl); // redis 에서 SET bl:access:<JTI> 45

            // refreshtoken 삭제
            redisTokenService.deleteRefreshToken(userId, request.getDeviceId());

            // 쿠키 삭제
            CookieUtil.clearRefreshCookie(response);

            return ResponseEntity.ok(Map.of("result", "로그아웃 성공"));
        } catch(Exception e){
            return ResponseEntity.ok(Map.of("result", "로그아웃 실패"));
        }
    }




}
