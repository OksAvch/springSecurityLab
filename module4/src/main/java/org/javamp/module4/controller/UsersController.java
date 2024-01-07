package org.javamp.module4.controller;

import lombok.AllArgsConstructor;
import org.javamp.module4.data.ChangePasswordDto;
import org.javamp.module4.exception.IncorrectUserOrPassword;
import org.javamp.module4.exception.UserNotFoundException;
import org.javamp.module4.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UsersController {

    private UserServiceImpl service;

    @PatchMapping("/changePassword")
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) throws IncorrectUserOrPassword, UserNotFoundException {

        service.changePassword(changePasswordDto);
    }

    @GetMapping("/blocked")
    public ResponseEntity<List<String>> getBlockedUsers() {
        return new ResponseEntity<>(service.getBlockedUsers(), HttpStatus.OK);
    }
}
