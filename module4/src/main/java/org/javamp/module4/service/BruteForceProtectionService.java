package org.javamp.module4.service;

public interface BruteForceProtectionService {
    void registerLoginFailure(String username);

    void resetBruteForceCounter(String username);

    void enableUsersIfTimeIsUp();
}
