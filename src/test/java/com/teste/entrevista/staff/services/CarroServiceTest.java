package com.teste.entrevista.staff.service;

import com.teste.entrevista.staff.event.EventPublisher;
import com.teste.entrevista.staff.model.Carro;
import com.teste.entrevista.staff.repository.CarroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarroServiceTest {

    @Mock
    private CarroRepository carroRepository;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private CarroService carroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPublishEventWhenCarIsCreated() {
        Carro carro = new Carro();
        carro.setModelo("Fusca");
        carro.setCor("Azul");
        carro.setPlaca("XYZ-1234");

        when(carroRepository.save(carro)).thenReturn(carro);

        carroService.criarCarro(carro);

        verify(eventPublisher).publishCarCreatedEvent("New car created with ID: " + carro.getId());
    }
}
