package org.javamp.module4.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javamp.module4.service.BruteForceProtectionService;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AuthenticationEvents {

    private final BruteForceProtectionService bruteForceProtectionService;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event) {
        log.info("Authentication was performed successfully");
        String username = event.getAuthentication().getName();
        bruteForceProtectionService.resetBruteForceCounter(username);
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event) {
        log.info("Authentication failure detected");
        String username = event.getAuthentication().getName();
        bruteForceProtectionService.registerLoginFailure(username);
    }
}
