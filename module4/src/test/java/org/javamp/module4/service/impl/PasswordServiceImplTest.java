package org.javamp.module4.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordServiceImplTest {

    private static final String PASSWORD = "password";
    private static final String ENCODED_PASSWORD = "encoded";

    @Mock
    PasswordEncoder passwordEncoderMock;

    @InjectMocks
    PasswordServiceImpl sut;

    @Test
    void shouldEncrypt() {
        when(passwordEncoderMock.encode(PASSWORD)).thenReturn(ENCODED_PASSWORD);

        assertEquals(ENCODED_PASSWORD, sut.encode(PASSWORD));
        verify(passwordEncoderMock, times(1)).encode(PASSWORD);
    }

    @Test
    void shouldValidate() {
        when(passwordEncoderMock.matches(PASSWORD, ENCODED_PASSWORD)).thenReturn(true);

        assertTrue(sut.validate(PASSWORD, ENCODED_PASSWORD));
        verify(passwordEncoderMock, times(1)).matches(PASSWORD, ENCODED_PASSWORD);
    }
}