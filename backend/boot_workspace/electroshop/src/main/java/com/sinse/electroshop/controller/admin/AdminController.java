package com.sinse.electroshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/store/main")
    public String main(){
        return "store/index";
    }
}
