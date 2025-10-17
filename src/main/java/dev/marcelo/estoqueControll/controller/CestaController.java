package dev.marcelo.estoqueControll.controller;

import dev.marcelo.estoqueControll.dto.CestaCreateDto;
import dev.marcelo.estoqueControll.dto.CestaFamiliaDto;
import dev.marcelo.estoqueControll.dto.GuardarAlimentoDto;
import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.model.Cesta;
import dev.marcelo.estoqueControll.service.CestaService;
import dev.marcelo.estoqueControll.service.FamiliaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cesta")
public class CestaController {

    private final CestaService cestaService;
    private final FamiliaService familiaService;

    @PostMapping
    public ResponseEntity<Cesta> create(@RequestBody CestaCreateDto dto){
        Cesta cesta = cestaService.create(dto);
        return ResponseEntity.status(201).body(cesta);
    }

    @GetMapping
    public ResponseEntity<List<Cesta>> findAll(){
        List<Cesta> cestas = cestaService.findAllCestas();
        return ResponseEntity.ok().body(cestas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cesta> findById(@PathVariable Long id){
        Cesta cesta = cestaService.findCesta(id);
        return ResponseEntity.ok().body(cesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        cestaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/guardaralimento")
    public ResponseEntity<Alimento> guardarAlimentoNoDeposito(@RequestBody GuardarAlimentoDto dto){
       cestaService.guardarAlimento(dto.id());
       return ResponseEntity.noContent().build();
    }

    @PostMapping("/entregar")
    public ResponseEntity<CestaFamiliaDto> entregarCesta(@RequestBody CestaFamiliaDto dto){
        Cesta cestaDafamilia = cestaService.findCesta(dto.cestaId());
        CestaFamiliaDto cestaRecebida = cestaService.entregarCesta(cestaDafamilia, dto.nomeDaFamilia());
        return ResponseEntity.ok().body(cestaRecebida);
    }

    @GetMapping("/{id}/alimentos")
    public ResponseEntity<List<Alimento>> getAlimentosDaCesta(@PathVariable Long id){
        List<Alimento> alimentos = cestaService.verificarAlimentos(id);
        return ResponseEntity.ok(alimentos);
    }
}
