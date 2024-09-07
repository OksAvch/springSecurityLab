package org.javamp.module4.service.impl;

import org.javamp.module4.data.SecretData;
import org.javamp.module4.dto.SecretDto;
import org.javamp.module4.repository.SecretsRepository;
import org.javamp.module4.service.SecretService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class SecretServiceImpl implements SecretService {
    private static final String SHARING_URL_PATH = "getSecret";
    public static final String TOKEN_PARAMETER = "token";

    private final SecretsRepository repository;
    private final String host;
    private final String port;
    private final String scheme;

    public SecretServiceImpl(SecretsRepository repository,
                             @Value("${app.link.host}") String host,
                             @Value("${app.link.port}") String port,
                             @Value("${app.link.scheme}") String scheme) {

        this.repository = repository;
        this.host = host;
        this.port = port;
        this.scheme = scheme;
    }

    @Override
    public String saveSecret(SecretDto secret) {
        String token = repository.save(new SecretData(secret.getData())).getId();

        return UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(host)
                .port(port)
                .path(SHARING_URL_PATH)
                .queryParam(TOKEN_PARAMETER, token)
                .toUriString();
    }

    @Override
    public Optional<String> getSecret(String token) {
        Optional<SecretData> secretData = repository.findById(token);
        secretData.ifPresent(repository::delete);

        return secretData
                .map(SecretData::getData);
    }
}
