package dev.marcelo.estoqueControll.controller;

import dev.marcelo.estoqueControll.model.Familia;
import dev.marcelo.estoqueControll.service.FamiliaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/familia")
public class FamiliaController {

    private final FamiliaService familiaService;

    @PostMapping
    public ResponseEntity<Familia> create(@RequestBody Familia familia){
       Familia familiaCadastrada = familiaService.cadastrar(familia);
       return ResponseEntity.status(201).body(familiaCadastrada);
    }

    @GetMapping()
    public ResponseEntity<List<Familia>> findAll(){
        List<Familia> familias = familiaService.findAllFamilias();
        return ResponseEntity.ok().body(familias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Familia> findByID(@PathVariable Long id){
        Familia familia = familiaService.findById(id);
        return ResponseEntity.ok().body(familia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        familiaService.deletarFamilia(id);
        return ResponseEntity.noContent().build();
    }
}
