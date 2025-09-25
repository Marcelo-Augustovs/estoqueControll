package dev.marcelo.estoqueControll.service;

import dev.marcelo.estoqueControll.infra.exceptions.ApiNotFoundException;
import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.model.Deposito;
import dev.marcelo.estoqueControll.repository.DepositoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositoService {

    private final DepositoRepository depositoRepository;

    @Transactional
    public Deposito adicionarAlimento(Alimento alimento, int mes, int ano) {
        Deposito deposito = depositoRepository.findByMesAndAno(mes, ano)
                .orElseGet(() -> new Deposito(alimento, mes, ano));

        if (deposito.getId() != null) {
            deposito.adicionarAlimento(alimento);
        }

        return depositoRepository.save(deposito);
    }

    @Transactional
    public void atualizarAlimento(Alimento alimento, int mes, int ano) {
        Deposito deposito = depositoRepository.findByMesAndAno(mes, ano)
                .orElseThrow(() -> new ApiNotFoundException("Depósito não encontrado"));

        deposito.getAlimentos().stream()
                .filter(a -> a.getId().equals(alimento.getId()))
                .findFirst()
                .ifPresent(a -> {
                    a.setNome(alimento.getNome());
                    a.setTipoDoAlimento(alimento.getTipoDoAlimento());
                    a.setMarca(alimento.getMarca());
                });
    }

    @Transactional
    public void retirarAlimento(Alimento alimento) {
        Deposito deposito = depositoRepository.findByMesAndAno(
                alimento.getDataCriacao().getMonthValue(),
                alimento.getDataCriacao().getYear()
        ).orElseThrow(() -> new ApiNotFoundException("Alimento nao encontrado no deposito"));

        deposito.getAlimentos().remove(alimento);
        deposito.setQuantidadeDeAlimentos(deposito.getAlimentos().size());

        // Salva o depósito
        depositoRepository.save(deposito);
    }

    @Transactional(readOnly = true)
    public Deposito findById(Long id) {
       return  depositoRepository.findById(id)
               .orElseThrow( () -> new ApiNotFoundException(String.format("Deposito do mes %n nao encontrado",id)));
    }

    @Transactional(readOnly = true)
    public List<Deposito> findAllDepositos() {
        return depositoRepository.findAll();
    }
}
