package com.cpp.loginandregister.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {


    @RequestMapping("/page/login")
    public String toLogin(HttpServletRequest request){

        return "login";
    }
}
