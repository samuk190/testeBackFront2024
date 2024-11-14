package com.teste.entrevista.staff.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.teste.entrevista.staff.model.Carro;
import com.teste.entrevista.staff.service.CarroService;
public class CarroControllerTest {

    @Mock
    private CarroService carroService;

    @InjectMocks
    private CarroController carroController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarCarro() {
        Carro carro = new Carro();
        carro.setModelo("Fusca");
        carro.setCor("Azul");
        carro.setPlaca("XYZ-1234");

        when(carroService.criarCarro(carro)).thenReturn(carro);

        ResponseEntity<Carro> response = carroController.criarCarro(carro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carro, response.getBody());
    }
}
