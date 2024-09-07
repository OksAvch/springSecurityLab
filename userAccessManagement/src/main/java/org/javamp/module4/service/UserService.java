package org.javamp.module4.service;

import org.javamp.module4.dto.ChangePasswordDto;
import org.javamp.module4.exception.IncorrectUserOrPassword;
import org.javamp.module4.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    void changePassword(ChangePasswordDto changePasswordDto) throws IncorrectUserOrPassword, UserNotFoundException;

    List<String> getBlockedUsers();
}
