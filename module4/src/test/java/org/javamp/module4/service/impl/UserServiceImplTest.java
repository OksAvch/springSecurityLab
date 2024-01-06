package org.javamp.module4.service.impl;

import lombok.SneakyThrows;
import org.javamp.module4.data.ChangePasswordDto;
import org.javamp.module4.data.UserData;
import org.javamp.module4.exception.IncorrectUserOrPassword;
import org.javamp.module4.exception.UserNotFoundException;
import org.javamp.module4.repository.UserRepository;
import org.javamp.module4.service.PasswordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final String USER = "user";
    public static final String NEW_PASSWORD = "new";
    public static final String ENCODED_NEW = "encodedNew";
    public static final String OLD_PASSWORD = "old";
    public static final String WRONG_PASSWORD = "wrong";
    public static final String ENCODED_OLD = "encoded";
    @Mock
    PasswordService passwordServiceMock;

    @Mock
    UserRepository repositoryMock;

    @InjectMocks
    UserServiceImpl sut;

    @Test
    @SneakyThrows
    void shouldChangePassword() {
        UserData userData = new UserData(USER, ENCODED_OLD, true);
        ChangePasswordDto changePasswordDto = new ChangePasswordDto(USER, OLD_PASSWORD, NEW_PASSWORD);

        when(repositoryMock.findById(USER)).thenReturn(Optional.of(userData));
        when(passwordServiceMock.validate(OLD_PASSWORD, ENCODED_OLD)).thenReturn(true);
        when(passwordServiceMock.encode(NEW_PASSWORD)).thenReturn(ENCODED_NEW);

        sut.changePassword(changePasswordDto);

        verify(repositoryMock, times(1)).save(new UserData(USER, ENCODED_NEW, true));
    }

    @Test
    @SneakyThrows
    void shouldFailOnIncorrectOldPassword() {
        UserData userData = new UserData(USER, ENCODED_OLD, true);
        ChangePasswordDto changePasswordDto = new ChangePasswordDto(USER, WRONG_PASSWORD, NEW_PASSWORD);

        when(repositoryMock.findById(USER)).thenReturn(Optional.of(userData));
        when(passwordServiceMock.validate(WRONG_PASSWORD, ENCODED_OLD)).thenReturn(false);

        assertThrows(IncorrectUserOrPassword.class, () -> sut.changePassword(changePasswordDto));
        verify(repositoryMock, times(0)).save(new UserData(USER, ENCODED_NEW, true));
    }

    @Test
    @SneakyThrows
    void shouldFailOnUserNotFound() {
        ChangePasswordDto changePasswordDto = new ChangePasswordDto(USER, WRONG_PASSWORD, NEW_PASSWORD);

        when(repositoryMock.findById(USER)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> sut.changePassword(changePasswordDto));
        verify(repositoryMock, times(0)).save(new UserData(USER, ENCODED_NEW, true));
    }
}