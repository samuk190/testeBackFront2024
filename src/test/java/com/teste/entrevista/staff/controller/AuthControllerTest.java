package com.teste.entrevista.staff.controller;

import com.teste.entrevista.staff.model.LoginRequest;
import com.teste.entrevista.staff.service.UserService;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Key;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Key secretKey; // Ajuste aqui para usar uma chave válida

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializa uma chave válida para o algoritmo HS512
        secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

        // Passa a chave inicializada para o AuthController
        authController = new AuthController(authenticationManager, userService, passwordEncoder,secretKey);
        // Utilize a chave gerada no teste
    }

    @Test
    void shouldGenerateJwtTokenOnSuccessfulLogin() {
        // Mock de autenticação bem-sucedida
        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("user");
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);

        // Mock do LoginRequest
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("password");

        // Execução do método login
        Map<String, String> response = authController.login(loginRequest);

        // Validações
        assertNotNull(response.get("token"), "Token não deve ser nulo");
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void shouldThrowExceptionOnInvalidLogin() {
        // Mock de falha na autenticação
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(BadCredentialsException.class);

        // Mock do LoginRequest com credenciais inválidas
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("invalidUser");
        loginRequest.setPassword("wrongPassword");

        // Verificação de exceção ao tentar fazer login com credenciais inválidas
        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            authController.login(loginRequest);
        });

        // Validações
        assertEquals("Credenciais inválidas", exception.getMessage());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }
}
