package com.teste.entrevista.staff.service;

import com.teste.entrevista.staff.event.EventPublisher;
import com.teste.entrevista.staff.model.Cliente;
import com.teste.entrevista.staff.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EventPublisher eventPublisher;

    public ClienteService(ClienteRepository clienteRepository, EventPublisher eventPublisher) {
        this.clienteRepository = clienteRepository;
        this.eventPublisher = eventPublisher;
    }

    public Cliente criarCliente(Cliente cliente) {
        Cliente savedClient = clienteRepository.save(cliente);
        eventPublisher.publishCustomerCreatedEvent("New customer created with ID: " + savedClient.getId());
        return savedClient;
    }
}
