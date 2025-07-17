package com.samar.secureApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @RequestMapping("/logout-success")
    public String logoutPage(){
        return "logout";
    }
}
