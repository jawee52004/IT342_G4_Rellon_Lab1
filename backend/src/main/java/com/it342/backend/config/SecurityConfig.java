package com.it342.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for API
                .cors(cors -> cors.disable()) // optional, handled by @CrossOrigin
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // allow all requests

        return http.build();
    }
}
