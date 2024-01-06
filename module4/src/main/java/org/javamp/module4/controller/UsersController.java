package org.javamp.module4.controller;

import lombok.AllArgsConstructor;
import org.javamp.module4.data.ChangePasswordDto;
import org.javamp.module4.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UsersController {

    private UserServiceImpl service;

    @PatchMapping("/changePassword")
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) throws Exception {

        service.changePassword(changePasswordDto);
    }
}
