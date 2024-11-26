package com.teste.entrevista.staff.service;

import com.teste.entrevista.staff.event.EventPublisher;
import com.teste.entrevista.staff.model.Atendimento;
import com.teste.entrevista.staff.repository.AtendimentoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AtendimentoService {

   private final AtendimentoRepository atendimentoRepository;
    private final EventPublisher eventPublisher;

    public AtendimentoService(AtendimentoRepository atendimentoRepository, EventPublisher eventPublisher) {
        this.atendimentoRepository = atendimentoRepository;
        this.eventPublisher = eventPublisher;
    }

    public Atendimento criarAtendimento(Atendimento atendimento) {
        Atendimento savedAppointment = atendimentoRepository.save(atendimento);
        eventPublisher.publishAppointmentCreatedEvent("New appointment created with ID: " + savedAppointment.getId());
        return savedAppointment;
    }

    public List<Atendimento> buscarTodosAtendimentos() {
        return atendimentoRepository.findAll();
    }
}
