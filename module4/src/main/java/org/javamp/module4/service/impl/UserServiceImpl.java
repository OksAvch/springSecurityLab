package org.javamp.module4.service.impl;

import lombok.AllArgsConstructor;
import org.javamp.module4.data.UserData;
import org.javamp.module4.dto.ChangePasswordDto;
import org.javamp.module4.exception.IncorrectUserOrPassword;
import org.javamp.module4.exception.UserNotFoundException;
import org.javamp.module4.repository.UserRepository;
import org.javamp.module4.service.PasswordService;
import org.javamp.module4.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private PasswordService passwordService;
    private UserRepository userRepository;

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) throws IncorrectUserOrPassword, UserNotFoundException {
        Optional<UserData> userData = userRepository.findById(changePasswordDto.getUserName());

        if (userData.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        String expectedPassword = userData.get().getPassword();
        if (passwordService.validate(changePasswordDto.getOldPassword(), expectedPassword)) {
            updatePassword(changePasswordDto, userData.get());
        } else {
            throw new IncorrectUserOrPassword("Password is incorrect");
        }

    }

    @Override
    public List<String> getBlockedUsers() {
        return userRepository.findBlockedUsernames();
    }

    private void updatePassword(ChangePasswordDto changePasswordDto, UserData userData) {
        UserData updatedUserData = userData
                .toBuilder()
                .password(passwordService.encode(changePasswordDto.getNewPassword()))
                .build();
        userRepository.save(updatedUserData);
    }
}
