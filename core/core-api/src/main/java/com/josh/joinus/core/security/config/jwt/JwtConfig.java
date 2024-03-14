package com.josh.joinus.core.security.config.jwt;

import com.josh.joinus.core.security.token.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class JwtConfig {

    @Value("${spring.jwt.secret}")
    private String secret;
    private final UserDetailsService customUserDetailsService;

    @Bean
    public AuthTokenProvider jwtProvider() {
        return new AuthTokenProvider(secret, customUserDetailsService);
    }
}
