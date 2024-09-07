package org.javamp.module4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @GetMapping(path = "/info")
    public String getInternalInfo() {
        return "MVC application";
    }

    @GetMapping(path = "/home")
    public String getHome() {
        return "It is a Home page";
    }

    @GetMapping(path = "/contactInfo")
    public String getContactInfo() {
        return "It is a Contact information page";
    }

    @GetMapping(path = "/admin")
    public String getAdminInfo() {
        return "It is an Administration page";
    }
}
