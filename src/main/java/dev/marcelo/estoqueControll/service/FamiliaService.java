package dev.marcelo.estoqueControll.service;

import dev.marcelo.estoqueControll.infra.exceptions.ApiNotFoundException;
import dev.marcelo.estoqueControll.model.Familia;
import dev.marcelo.estoqueControll.repository.FamiliaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FamiliaService {

    private final FamiliaRepository familiaRepository;

    @Transactional
    public Familia cadastrar(Familia familia){
        return familiaRepository.save(familia);
    }

    @Transactional(readOnly = true)
    public Familia findById(Long id) {
        return familiaRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException(String.format("Familia id %n",id)));
    }

    @Transactional(readOnly = true)
    public List<Familia> findAllFamilias(){
        return familiaRepository.findAll();
    }

    @Transactional
    public void deletarFamilia(Long id){
        familiaRepository.delete(this.findById(id));
    }

    @Transactional(readOnly = true)
    public Familia findByNome(String nome) {
        return familiaRepository.findByNome(nome);
    }
}
