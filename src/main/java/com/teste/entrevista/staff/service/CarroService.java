package com.teste.entrevista.staff.service;

import com.teste.entrevista.staff.event.EventPublisher;
import com.teste.entrevista.staff.model.Carro;
import com.teste.entrevista.staff.repository.CarroRepository;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    private final CarroRepository carroRepository;
    private final EventPublisher eventPublisher;

    public CarroService(CarroRepository carroRepository, EventPublisher eventPublisher) {
        this.carroRepository = carroRepository;
        this.eventPublisher = eventPublisher;
    }

    public Carro criarCarro(Carro carro) {
        Carro savedCar = carroRepository.save(carro);
        eventPublisher.publishCarCreatedEvent("New car created with ID: " + savedCar.getId());
        return savedCar;
    }
}
