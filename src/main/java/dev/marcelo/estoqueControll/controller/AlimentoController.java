package dev.marcelo.estoqueControll.controller;

import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.service.AlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/alimento")
public class AlimentoController {

    private final AlimentoService alimentoService;

    @PostMapping
    public ResponseEntity<Alimento> create(@RequestBody Alimento dto){
        alimentoService.save(dto);
        return ResponseEntity.status(201).body(dto);
    }
}
