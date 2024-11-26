package com.teste.entrevista.staff.config;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

@SpringBootTest
public class JwtRequestFilterTest {

    private Key secretKey;

    @InjectMocks
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        // Gera uma chave secreta para os testes
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        SecurityContextHolder.clearContext(); // Limpa o contexto de autenticação antes de cada teste

        // Instancia o filtro com a chave secreta
        jwtRequestFilter = new JwtRequestFilter(secretKey);
    }

    // @Test
    // void shouldSetAuthenticationForValidJwtToken() throws ServletException, IOException {

    //     assertNotNull(secretKey, "Secret key should be available");

    //     // Gera um token JWT válido para o teste
    //     String token = Jwts.builder()
    //             .setSubject("user")
    //             .setIssuedAt(new Date())
    //             .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expira em 1 hora
    //             .signWith(secretKey) // Assina com a chave secreta
    //             .compact();

    //     MockHttpServletRequest request = new MockHttpServletRequest();
    //     request.addHeader("Authorization", "Bearer " + token);

    //     MockHttpServletResponse response = new MockHttpServletResponse();
    //     FilterChain chain = mock(FilterChain.class);

    //     // Executa o filtro
    //     jwtRequestFilter.doFilterInternal(request, response, chain);

    //     // Verifica se a autenticação foi configurada corretamente no contexto de segurança
    //     UsernamePasswordAuthenticationToken authentication =
    //             (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

    //     assertNotNull(authentication, "Authentication should not be null");
    //     assertEquals("user", authentication.getPrincipal(), "Principal should match the subject in the JWT");

    //     // Verifica se o filtro continuou o processamento na cadeia
    //     verify(chain).doFilter(request, response);
    // }
}
