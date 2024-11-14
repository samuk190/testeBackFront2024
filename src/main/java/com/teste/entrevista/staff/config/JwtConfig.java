package com.teste.entrevista.staff.config;

import java.security.Key;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtConfig {
    @Bean
    public Key secretKey() {

        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}
