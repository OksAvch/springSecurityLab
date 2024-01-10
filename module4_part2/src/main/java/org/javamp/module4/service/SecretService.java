package org.javamp.module4.service;

import org.javamp.module4.dto.SecretDto;

import java.util.Optional;

public interface SecretService {
    String saveSecret(SecretDto data);
    Optional<String> getSecret(String token);

}
