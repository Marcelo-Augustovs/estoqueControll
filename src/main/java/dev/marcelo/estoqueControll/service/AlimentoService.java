package dev.marcelo.estoqueControll.service;

import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.repository.AlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    @Transactional
    public Alimento save(Alimento alimento){
        return alimentoRepository.save(alimento);
    }

}
