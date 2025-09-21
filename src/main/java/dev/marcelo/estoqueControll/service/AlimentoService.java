package dev.marcelo.estoqueControll.service;

import dev.marcelo.estoqueControll.infra.exceptions.ApiNotFoundException;
import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.repository.AlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

    @Transactional
    public Alimento save(Alimento alimento){
        return alimentoRepository.save(alimento);
    }

    @Transactional
    public Alimento updateAlimento(Long id, Alimento alimentoAtualizado) {
        Alimento alimento = alimentoRepository.findById(id).orElseThrow(
                () -> new ApiNotFoundException("Anotação não encontrada")
        );

        alimento.setNome(
                (alimentoAtualizado.getNome() == null || alimentoAtualizado.getNome().isEmpty())
                        ? alimento.getNome()
                        : alimentoAtualizado.getNome()
        );

        alimento.setTipoDoAlimento(
                (alimentoAtualizado.getTipoDoAlimento() == null || alimentoAtualizado.getTipoDoAlimento().isEmpty())
                        ? alimento.getTipoDoAlimento()
                        : alimentoAtualizado.getTipoDoAlimento()
        );

        alimento.setMarca(
                (alimentoAtualizado.getMarca() == null || alimentoAtualizado.getMarca().isEmpty())
                        ? alimento.getMarca()
                        : alimentoAtualizado.getMarca()
        );

        return alimento;
    }
    @Transactional
    public Alimento delete(Long id){
        Alimento alimento = alimentoRepository.findById(id).orElseThrow(
                () -> new ApiNotFoundException("Alimento não encontrado")
        );
        alimentoRepository.deleteById(alimento.getId());
        return alimento;
    }

    @Transactional(readOnly = true)
    public List<Alimento> findAllAlimentos() {
        List<Alimento> alimentos = new ArrayList<>();
        alimentos = alimentoRepository.findAll();
        return alimentos;
    }
    @Transactional(readOnly = true)
    public Alimento findAlimento(Long id) {
        return alimentoRepository.findById(id).orElseThrow(
                () -> new ApiNotFoundException("Alimento não encontrado")
        );
    }

    @Transactional(readOnly = true)
    public Alimento findByNome(String nomeDoAlimento) {
        return alimentoRepository.findFirstByNome(nomeDoAlimento)
                .orElseThrow(() -> new ApiNotFoundException("Alimento nao encontrado " + nomeDoAlimento));
    }
}
