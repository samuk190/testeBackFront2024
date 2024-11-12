package com.teste.entrevista.staff.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

@Entity
@Data
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Carro veiculo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDate dataAgendamento;
    private LocalTime horarioAgendamento;

    @Enumerated(EnumType.STRING)
    private TipoAtendimento tipo;

    @Enumerated(EnumType.STRING)
    private StatusAtendimento status;

    public enum TipoAtendimento {
        REPARO, MANUTENCAO_PREVENTIVA, LIMPEZA
    }

    public enum StatusAtendimento {
        AGENDADO, EM_ANDAMENTO, CONCLUIDO
    }
}
