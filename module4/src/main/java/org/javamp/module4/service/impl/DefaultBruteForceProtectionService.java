package org.javamp.module4.service.impl;

import org.javamp.module4.data.UserData;
import org.javamp.module4.repository.UserRepository;
import org.javamp.module4.service.BruteForceProtectionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultBruteForceProtectionService implements BruteForceProtectionService {

    private final int maxFailedLogins;
    private final UserRepository userRepository;

    public DefaultBruteForceProtectionService(UserRepository userRepository,
                                              @Value("${app.security.failedlogin.count}") int maxFailedLogins) {
        this.userRepository = userRepository;
        this.maxFailedLogins = maxFailedLogins;
    }

    @Override
    public void registerLoginFailure(String username) {
        Optional<UserData> user = getUser(username);

        if (user.isPresent() && user.get().isEnabled()) {
            registerFailure(user.get());
        }
    }

    @Override
    public void resetBruteForceCounter(String username) {
        Optional<UserData> user = getUser(username);

        if (user.isPresent() && hasFailures(user.get())) {
            UserData userDataUpdate = createEnabledUserDataUpdate(user.get());
            userRepository.save(userDataUpdate);
        }
    }

    @Override
    public void enableUsersIfTimeIsUp() {
        List<UserData> disabledUsers = userRepository.findByEnabled(false);

        List<UserData> usersToEnable = disabledUsers.stream()
                .filter(u -> {
                    Long blockingTimestamp = Optional.ofNullable(u.getBlockingTimestamp()).orElse(Long.MAX_VALUE);
                    return isBlockingTimeUp(blockingTimestamp);
                })
                .map(this::createEnabledUserDataUpdate)
                .toList();

        userRepository.saveAll(usersToEnable);
    }

    private boolean hasFailures(UserData userData) {
        return userData.getFailedLoginAttempts() != 0;
    }

    private void registerFailure(UserData userData) {
        int failedCounter = userData.getFailedLoginAttempts();
        UserData.UserDataBuilder userUpdate = userData.toBuilder();

        if (maxFailedLogins < failedCounter + 1) {
            userUpdate.enabled(false);
            userUpdate.blockingTimestamp(getCurrentTimeStamp());
        } else {
            userUpdate.failedLoginAttempts(failedCounter + 1);
        }

        userRepository.save(userUpdate.build());
    }

    private Optional<UserData> getUser(final String username) {
        return userRepository.findById(username);
    }

    private LocalDateTime parseTimestamp(long timestampInMillis) {
        return Instant
                .ofEpochMilli(timestampInMillis)
                .atZone(ZoneOffset.UTC)
                .toLocalDateTime();
    }

    private boolean isBlockingTimeUp(long blockingTime) {
        return parseTimestamp(blockingTime)
                .plusMinutes(5)
                .isBefore(LocalDateTime.now());
    }

    private long getCurrentTimeStamp() {
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    private UserData createEnabledUserDataUpdate(UserData userData) {
        return userData.toBuilder()
                .enabled(true)
                .failedLoginAttempts(0)
                .blockingTimestamp(null)
                .build();
    }
}
