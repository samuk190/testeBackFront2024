package com.teste.entrevista.staff.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.teste.entrevista.staff.event.EventPublisher;
import com.teste.entrevista.staff.model.Atendimento;
import com.teste.entrevista.staff.repository.AtendimentoRepository;

public class AtendimentoServiceTest {

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private AtendimentoService atendimentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPublishEventWhenAppointmentIsCreated() {
        Atendimento atendimento = new Atendimento();

        when(atendimentoRepository.save(atendimento)).thenReturn(atendimento);

        atendimentoService.criarAtendimento(atendimento);

        verify(eventPublisher).publishAppointmentCreatedEvent("New appointment created with ID: " + atendimento.getId());
    }
}
