package org.javamp.module4.service;

public interface PasswordService {

    String encode(String password);

    boolean validate(String password, String expectedHash);
}
