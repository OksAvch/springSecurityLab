package org.javamp.module4.service;

import org.javamp.module4.data.ChangePasswordDto;
import org.javamp.module4.exception.IncorrectUserOrPassword;
import org.javamp.module4.exception.UserNotFoundException;

public interface UserService {
    void changePassword(ChangePasswordDto changePasswordDto) throws IncorrectUserOrPassword, UserNotFoundException;
}
