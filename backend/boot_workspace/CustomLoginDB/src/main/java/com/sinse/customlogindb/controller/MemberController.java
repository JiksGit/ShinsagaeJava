package com.sinse.customlogindb.controller;

import com.sinse.customlogindb.util.PasswordCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final PasswordCreator passwordCreator;

    @GetMapping("/")
    public String index() {
        String pwd = passwordCreator.getCryptPassword("Galaxy");
        log.debug(pwd);

        return "member/index";
    }

    @GetMapping("/loginform")
    public String loginForm() {
        return "member/login";
    }

    @GetMapping("/main")
    public String main(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("id", userDetails.getUsername());
        return "member/index";
    }
}
