package com.sinse.chatroomapp.controller;

import com.sinse.chatroomapp.domain.Member;
import com.sinse.chatroomapp.exception.MemberException;
import com.sinse.chatroomapp.model.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/list")
    public ModelAndView memberList(ModelAndView mav) {
        List<Member> memberList =  memberService.selectAll();

        mav.setViewName("member/list");
        mav.addObject("memberList",  memberList);

        return mav;
    }

    @GetMapping("/member/regist")
    public String registerForm(){
        return "member/regist";
    }

    @PostMapping("/member/join")
    public String join(Member member){
        try{
            memberService.regist(member);
        } catch (MemberException e){
            e.printStackTrace();
        }

        return "redirect:/member/list";
    }

    @GetMapping("/member/loginform")
    public String loginForm(){
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(Member member, HttpSession session){
        Member mem = memberService.selectById(member.getId());

        if(mem == null ){
            return "redirect:/member/loginform";
        }
        session.setAttribute("member", mem);

        return "redirect:/member/list";
    }
}
