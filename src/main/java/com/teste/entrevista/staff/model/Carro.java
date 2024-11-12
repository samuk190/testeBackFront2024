package com.teste.entrevista.staff.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String modelo;

    @NotNull
    private String cor;

    @NotNull
    @Column(unique = true)
    private String placa;

    @NotNull
    @Column(unique = true)
    private String chassi;

    @NotNull
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
