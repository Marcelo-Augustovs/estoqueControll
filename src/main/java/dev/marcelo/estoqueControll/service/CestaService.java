package dev.marcelo.estoqueControll.service;

import dev.marcelo.estoqueControll.dto.CestaCreateDto;
import dev.marcelo.estoqueControll.infra.exceptions.ApiNotFoundException;
import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.model.Cesta;
import dev.marcelo.estoqueControll.repository.CestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CestaService {

    private final CestaRepository cestaRepository;

    @Transactional
    public Cesta create(CestaCreateDto dto) {
         Cesta cesta = new Cesta(dto.mes());
         return cestaRepository.save(cesta);
    }

    @Transactional
    public void adicionarAlimento(Long id,Alimento alimento) {
      Cesta cesta = cestaRepository.findById(id)
                .orElseThrow( () -> new ApiNotFoundException("Cesta nao encontrada"));
      List<Alimento> lista = cesta.getAlimentos();
      lista.add(alimento);
      cesta.setQuantidadeDeAlimentos(lista.size());
    }
}
