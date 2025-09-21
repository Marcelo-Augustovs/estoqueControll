package dev.marcelo.estoqueControll.controller;

import dev.marcelo.estoqueControll.dto.AdicionarAlimentoDto;
import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.service.AlimentoService;
import dev.marcelo.estoqueControll.service.CestaService;
import dev.marcelo.estoqueControll.service.DepositoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/deposito")
public class DepositoController {

    private final DepositoService depositoService;
    private final AlimentoService alimentoService;
    private final CestaService cestaService;

    @PostMapping("/retirar")
    public ResponseEntity<Alimento> retirarAlimentoParaCesta(@RequestBody AdicionarAlimentoDto dto){
        Alimento alimento = alimentoService.findByNome(dto.nomeDoAlimento());
        cestaService.adicionarAlimento(dto.idDaCesta(),alimento);
        depositoService.retirarAlimentos(alimento);
        alimentoService.delete(alimento.getId());

        return ResponseEntity.ok().body(alimento);
    }
}
