package com.teste.entrevista.staff.controller;

import com.teste.entrevista.staff.model.Atendimento;
import com.teste.entrevista.staff.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;
    @GetMapping
    public  ResponseEntity<List<Atendimento>> getAtendimentos(@RequestParam String status, @RequestParam int page, @RequestParam int size) {
        List<Atendimento> atendimentos = atendimentoService.buscarTodosAtendimentos();

    return ResponseEntity.ok(atendimentos);
    }
    @PostMapping
    public ResponseEntity<Atendimento> criarAtendimento(@RequestBody Atendimento atendimento) {
        Atendimento novoAtendimento = atendimentoService.criarAtendimento(atendimento);
        return ResponseEntity.ok(novoAtendimento);
    }
}
