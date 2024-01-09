package org.javamp.module4.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationFailureHandler eventAuthenticationFailureHandler;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/", "/login", "/error", "/contactInfo")
                        .permitAll())

                .authorizeHttpRequests(auth -> auth.requestMatchers("/home")
                        .hasRole(Role.USER.name()))

                .authorizeHttpRequests(auth -> auth.requestMatchers("/admin")
                        .hasRole(Role.VIEW_ADMIN.name()))

                .authorizeHttpRequests(auth -> auth.requestMatchers("/info")
                        .hasAnyRole(Role.VIEW_INFO.name(), Role.VIEW_ADMIN.name()))

                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())

                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .failureHandler(eventAuthenticationFailureHandler)
                                .defaultSuccessUrl("/home")
//                                .failureUrl("/login?error")  // prevents LoginAuthenticationFailureHandler from being called
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/login?logout")
//                                .permitAll()  // why manual may suggest it to be here?
                )
                .passwordManagement(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder createPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public GrantedAuthoritiesMapper createAuthoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        return authorityMapper;
    }
}
