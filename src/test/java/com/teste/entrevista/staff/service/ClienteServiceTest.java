package com.teste.entrevista.staff.service;

import com.teste.entrevista.staff.event.EventPublisher;
import com.teste.entrevista.staff.model.Cliente;
import com.teste.entrevista.staff.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPublishEventWhenCustomerIsCreated() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setDocumento("123.456.789-00");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        clienteService.criarCliente(cliente);

        verify(eventPublisher).publishCustomerCreatedEvent("New customer created with ID: " + cliente.getId());
    }
}
