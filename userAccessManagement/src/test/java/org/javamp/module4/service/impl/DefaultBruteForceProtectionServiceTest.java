package org.javamp.module4.service.impl;

import org.javamp.module4.data.AuthoritiesData;
import org.javamp.module4.data.UserData;
import org.javamp.module4.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultBruteForceProtectionServiceTest {
    private static final String USER1 = "user1";
    private static final String USER2 = "user2";
    private static final String PASSWORD = "password";

    UserRepository repositoryMock = mock(UserRepository.class);
    DefaultBruteForceProtectionService sut = new DefaultBruteForceProtectionService(repositoryMock, 2);

    @Test
    void shouldRegisterLoginFailureAndBlock() {
        List<AuthoritiesData> authorities = Collections.singletonList(new AuthoritiesData());
        UserData incomingUser = new UserData(USER1, PASSWORD, true, 2, null, authorities);

        when(repositoryMock.findById(USER1)).thenReturn(Optional.of(incomingUser));
        sut.registerLoginFailure(USER1);

        verify(repositoryMock, times(1)).save(argThat(savedUser ->
                !savedUser.isEnabled()
                && Objects.equals(savedUser.getUserName(), incomingUser.getUserName())
                && Objects.equals(savedUser.getFailedLoginAttempts(), incomingUser.getFailedLoginAttempts())
                && !Objects.isNull(savedUser.getBlockingTimestamp())
        ));
    }

    @Test
    void shouldDoNothingIfBlock() {
        List<AuthoritiesData> authorities = Collections.singletonList(new AuthoritiesData());
        UserData incomingUser = new UserData(USER1, PASSWORD, false, 2, 1L, authorities);

        when(repositoryMock.findById(USER1)).thenReturn(Optional.of(incomingUser));
        sut.registerLoginFailure(USER1);

        verify(repositoryMock, times(0)).save(argThat(savedUser ->
                Objects.equals(savedUser.getUserName(), incomingUser.getUserName())));
    }

    @Test
    void shouldResetBruteForceCounter() {
        List<AuthoritiesData> authorities = Collections.singletonList(new AuthoritiesData());
        UserData incomingUser = new UserData(USER1, PASSWORD, true, 2, null, authorities);
        UserData expectedUser = new UserData(USER1, PASSWORD, true, 0, null, authorities);

        when(repositoryMock.findById(USER1)).thenReturn(Optional.of(incomingUser));
        sut.resetBruteForceCounter(USER1);

        verify(repositoryMock, times(1))
                .save(expectedUser);
    }

    @Test
    void shouldEnableUsersIfTimeIsUp() {
        long currentTime = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        long passedTime = LocalDateTime.now().minusMinutes(6).toInstant(ZoneOffset.UTC).toEpochMilli();
        List<AuthoritiesData> authorities = Collections.singletonList(new AuthoritiesData());
        UserData userToEnable = new UserData(USER1, PASSWORD, false, 2, passedTime, authorities);
        UserData userBlocked = new UserData(USER2, PASSWORD, false, 2, currentTime, authorities);
        UserData expectedUser = new UserData(USER1, PASSWORD, true, 0, null, authorities);

        doReturn(List.of(userBlocked, userToEnable)).when(repositoryMock).findByEnabled(false);
        sut.enableUsersIfTimeIsUp();

        verify(repositoryMock, times(1)).saveAll(List.of(expectedUser));
    }

}