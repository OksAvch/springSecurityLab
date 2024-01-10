package org.javamp.module4.controller;

import lombok.AllArgsConstructor;
import org.javamp.module4.dto.SecretDto;
import org.javamp.module4.service.SecretService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class HomeController {
    private static final String DATA_NOT_FOUND_ERROR = "THERE WAS NO INFORMATION FOUND BY REQUESTED URL";
    public static final String GETSECRET_PAGE = "getsecret";

    SecretService service;

    @GetMapping("/")
    public String getHome() {
        return "login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/shareSecret")
    public String getSharePage() {
        return "sharesecret";
    }

    @PostMapping(path = "/save")
    public String saveSecret(SecretDto secretDto, Model model) {
        String urlToShare = service.saveSecret(secretDto);

        model.addAttribute("message", urlToShare);

        return GETSECRET_PAGE;
    }

    @GetMapping("/getSecret")
    public String getSharedPage(
            @RequestParam(name = "token", defaultValue = "default1") String token,
            Model model) {

        Optional<String> secret = service.getSecret(token);

        if(secret.isPresent()){
             model.addAttribute("message", secret.get());
        } else {
            model.addAttribute("error", DATA_NOT_FOUND_ERROR);
        }
        return GETSECRET_PAGE;

    }
}
