package com.sinse.jwtredis.controller;

import com.sinse.jwtredis.Domain.CustomUserDetails;
import com.sinse.jwtredis.Domain.Member;
import com.sinse.jwtredis.controller.dto.MemberDTO;
import com.sinse.jwtredis.model.member.MemberService;
import com.sinse.jwtredis.model.member.RegistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class MemberController {

    private AuthenticationManager authenticationManager;
    private RegistService registService;
    private final MemberService memberService;

    public MemberController(RegistService registService, MemberService memberService, AuthenticationManager authenticationManager) {
        this.registService = registService;
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/member/regist")
    public ResponseEntity<?> regist(@RequestBody MemberDTO memberDTO) {
        log.debug("회원가입을 위해 사용자가 넣은 파라미터는 : {}", memberDTO);

        Member member = new  Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPwd());

        memberService.regist(member);
//        registService.regist(member);

        return ResponseEntity.ok(Map.of("status","ok"));
    }

    // 로그인 요청 처리
    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO memberDTO) {

        log.debug("개발자 정의 컨트롤러 로그인 요청 받음");

        Member member = new  Member();
        member.setId(memberDTO.getId());
        member.setPassword(memberDTO.getPwd());

        // 인증 시도
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getId(), member.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        log.debug("인증받은 회원의 아이디는 " + userDetails.getUsername());
        log.debug("인증받은 회원의 이메일은 " + userDetails.getEmail());

        // 인증에 성공하면 AccessToken(값) != RefreshToken(값) - 재발급의 대상이 되는 지 검증
        
        return ResponseEntity.ok("로그인 성공");
    }
}
