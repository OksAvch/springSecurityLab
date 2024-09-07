package org.javamp.module4.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
        implements ApplicationEventPublisherAware {

    public static final String LOGIN_DISABLED_RESPOSE_URL = "/login?disabled";
    public static final String BAD_CREDENTIALS_RESPONSE_URL = "/login?error";
    protected ApplicationEventPublisher eventPublisher;

    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        if (exception instanceof DisabledException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect(LOGIN_DISABLED_RESPOSE_URL);
            log.info("User is disabled");
        } else if (exception instanceof BadCredentialsException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect(BAD_CREDENTIALS_RESPONSE_URL);
            log.info("Bad credentials");
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
