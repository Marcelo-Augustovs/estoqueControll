package dev.marcelo.estoqueControll.controller;

import dev.marcelo.estoqueControll.dto.CestaCreateDto;
import dev.marcelo.estoqueControll.model.Cesta;
import dev.marcelo.estoqueControll.service.CestaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cesta")
public class CestaController {

    private final CestaService cestaService;

    @PostMapping
    public ResponseEntity<Cesta> create(@RequestBody CestaCreateDto dto){
        Cesta cesta = cestaService.create(dto);
        return ResponseEntity.status(201).body(cesta);
    }
}
