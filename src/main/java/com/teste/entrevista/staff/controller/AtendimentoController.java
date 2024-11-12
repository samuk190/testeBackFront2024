package com.teste.entrevista.staff.controller;

import com.teste.entrevista.staff.model.Atendimento;
import com.teste.entrevista.staff.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<Atendimento> criarAtendimento(@RequestBody Atendimento atendimento) {
        Atendimento novoAtendimento = atendimentoService.criarAtendimento(atendimento);
        return ResponseEntity.ok(novoAtendimento);
    }
}
