package dev.marcelo.estoqueControll.controller;

import dev.marcelo.estoqueControll.dto.AdicionarAlimentoDto;
import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.model.Deposito;
import dev.marcelo.estoqueControll.service.AlimentoService;
import dev.marcelo.estoqueControll.service.CestaService;
import dev.marcelo.estoqueControll.service.DepositoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/deposito")
public class DepositoController {

    private final DepositoService depositoService;
    private final AlimentoService alimentoService;
    private final CestaService cestaService;

    @PostMapping("/retirar")
    public ResponseEntity<Alimento> retirarAlimentoParaCesta(@RequestBody AdicionarAlimentoDto dto) {
        // Busca o alimento no banco (gerenciado pelo JPA)
        Alimento alimento = alimentoService.findByNome(dto.nomeDoAlimento());

        // Adiciona na cesta e persiste
        cestaService.adicionarAlimento(dto.idDaCesta(), alimento);

        // Remove do depósito e persiste
        depositoService.retirarAlimento(alimento);

        return ResponseEntity.ok(alimento);
    }


    @GetMapping
    public ResponseEntity<List<Deposito>> getAllDeposito(){
       List<Deposito> list = depositoService.findAllDepositos();
       return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Deposito> getById(@PathVariable Long id){
        Deposito deposito = depositoService.findById(id);
        return ResponseEntity.ok().body(deposito);
    }
}
