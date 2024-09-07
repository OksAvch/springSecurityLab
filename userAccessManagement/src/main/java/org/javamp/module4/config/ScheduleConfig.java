package org.javamp.module4.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamp.module4.service.impl.DefaultBruteForceProtectionService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
@AllArgsConstructor
public class ScheduleConfig {
    DefaultBruteForceProtectionService service;

    @Scheduled(fixedDelay = 60000)
    public void scheduleFixedDelayTask() {
        log.info("Initiating users check");

        service.enableUsersIfTimeIsUp();

        log.info("Users check was completed");
    }
}
