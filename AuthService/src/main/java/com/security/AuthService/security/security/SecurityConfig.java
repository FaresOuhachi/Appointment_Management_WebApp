package com.security.AuthService.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public HttpSecurity httpSecurity(HttpSecurity http) throws Exception {
        // Disabling CSRF and allowing authentication for certain endpoints
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/auth/**").permitAll()  // Allow all requests under /auth/*
                .anyRequest().authenticated();  // All other requests must be authenticated
        return http;
    }
}
