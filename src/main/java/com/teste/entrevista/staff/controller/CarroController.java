package com.teste.entrevista.staff.controller;

import com.teste.entrevista.staff.model.Carro;
import com.teste.entrevista.staff.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    public ResponseEntity<Carro> criarCarro(@RequestBody Carro carro) {
        Carro novoCarro = carroService.criarCarro(carro);
        return ResponseEntity.ok(novoCarro);
    }
}
