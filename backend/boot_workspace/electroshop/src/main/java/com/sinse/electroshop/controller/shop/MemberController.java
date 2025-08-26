package com.sinse.electroshop.controller.shop;

import com.sinse.electroshop.controller.dto.MemberDto;
import com.sinse.electroshop.domain.Member;
import com.sinse.electroshop.exception.MemberNotFoundException;
import com.sinse.electroshop.model.member.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/registform")
    public String registerForm() {
        return "electro/registform";
    }

    @GetMapping("/loginform")
    public String loginform(){
        return "electro/login";
    }

    @PostMapping("/regist")
    @ResponseBody
    public ResponseEntity<String> register(MemberDto memberDto){
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setPassword(memberDto.getPwd());
        member.setId(memberDto.getId());

        Member obj = memberService.regist(member);

        if(obj != null){
            return ResponseEntity.ok("success");
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<MemberDto> login(MemberDto memberDto, HttpSession session){

        log.debug("memberDto : "+ memberDto.toString());

        Member member = new Member();
        member.setId(memberDto.getId());
        member.setPassword(memberDto.getPwd());

        Member result = memberService.authenticate(member);
        session.setAttribute("member", result);

        memberDto.setPwd(null);
        memberDto.setName(result.getName());

        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    // 이 컨트롤러 내에서 발생하는 모든 예외를 처리할 수 있다 (@ExceptionHandler)
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleException(MemberNotFoundException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
