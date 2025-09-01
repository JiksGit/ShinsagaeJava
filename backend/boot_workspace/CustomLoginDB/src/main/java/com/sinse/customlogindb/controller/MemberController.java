package com.sinse.customlogindb.controller;

import com.sinse.customlogindb.util.PasswordCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
}
