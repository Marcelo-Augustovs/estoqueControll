package dev.marcelo.estoqueControll.controller;

import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.service.AlimentoService;
import dev.marcelo.estoqueControll.service.DepositoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/alimento")
public class AlimentoController {

    private final AlimentoService alimentoService;
    private final DepositoService depositoService;

    @PostMapping
    public ResponseEntity<Alimento> create(@RequestBody Alimento dto){
        Alimento alimento = alimentoService.save(dto);
        depositoService.adicionarAlimento(alimento,alimento.getDataCriacao().getMonthValue(),alimento.getDataCriacao().getYear());
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> findAlimento(@PathVariable Long id){
       Alimento alimentoSelecionado = alimentoService.findAlimento(id);
       return ResponseEntity.ok().body(alimentoSelecionado);
    }

    @GetMapping()
    public ResponseEntity<List<Alimento>> findAllAlimentos(){
        List<Alimento> todosAlimentos = alimentoService.findAllAlimentos();
        return ResponseEntity.ok().body(todosAlimentos);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Alimento> update(@PathVariable Long id, @RequestBody Alimento alimentoAtualizado){
        Alimento alimento = alimentoService.updateAlimento(id,alimentoAtualizado);
        return ResponseEntity.ok().body(alimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        alimentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
