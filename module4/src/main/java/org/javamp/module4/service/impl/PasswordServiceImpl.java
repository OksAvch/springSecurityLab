package org.javamp.module4.service.impl;

import lombok.AllArgsConstructor;
import org.javamp.module4.service.PasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordServiceImpl implements PasswordService {
    private PasswordEncoder encoder;

    @Override
    public String encode(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean validate(String password, String expectedHash) {
        return encoder.matches(password, expectedHash);
    }
}
