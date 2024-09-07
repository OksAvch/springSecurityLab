package org.javamp.module4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
