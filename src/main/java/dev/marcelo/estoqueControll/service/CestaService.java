package dev.marcelo.estoqueControll.service;

import dev.marcelo.estoqueControll.dto.CestaCreateDto;
import dev.marcelo.estoqueControll.dto.CestaFamiliaDto;
import dev.marcelo.estoqueControll.infra.exceptions.ApiNotFoundException;
import dev.marcelo.estoqueControll.model.Alimento;
import dev.marcelo.estoqueControll.model.Cesta;
import dev.marcelo.estoqueControll.model.Familia;
import dev.marcelo.estoqueControll.repository.CestaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CestaService {

    private final CestaRepository cestaRepository;
    private final DepositoService depositoService;
    private final FamiliaService familiaService;

    @Transactional
    public Cesta create(CestaCreateDto dto) {
         Cesta cesta = new Cesta(dto.mes());
         return cestaRepository.save(cesta);
    }

    @Transactional
    public void adicionarAlimento(Long idCesta, Alimento alimento) {
        Cesta cesta = cestaRepository.findById(idCesta)
                .orElseThrow(() -> new ApiNotFoundException("Cesta nao encontrada"));

        // Adiciona o alimento à lista
        cesta.getAlimentos().add(alimento);
        cesta.setQuantidadeDeAlimentos(cesta.getAlimentos().size());

        // Salva a cesta para persistir a alteração
        cestaRepository.save(cesta);
    }


    @Transactional(readOnly = true)
    public List<Cesta> findAllCestas(){
        return  cestaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cesta findCesta(Long id){
        return  cestaRepository.findById(id)
                .orElseThrow( () -> new ApiNotFoundException("cesta n encontrada"));
    }

    @Transactional
    public void delete(Long id) {
        cestaRepository.delete(this.findCesta(id));
    }

    @Transactional
    public void guardarAlimento(Long id) {
        Cesta cesta = this.findCesta(id);

        List<Alimento> alimentos = cesta.getAlimentos();
        if (!alimentos.isEmpty()) {
            Alimento alimento = alimentos.get(alimentos.size() - 1);
            depositoService.adicionarAlimento(alimento,alimento.getDataCriacao().getMonthValue(),alimento.getDataCriacao().getYear());
            alimentos.remove(alimento);
        }
    }

    @Transactional
    public CestaFamiliaDto entregarCesta(Cesta cesta, String nome){
        Familia familia = familiaService.findByNome(nome);
        familia.getCestasRecebidas().add(cesta);
        CestaFamiliaDto cestaFamiliaDto = new CestaFamiliaDto(familia.getNome(), cesta.getId(), cesta.getDataCriacao().getMonthValue());
        return cestaFamiliaDto;
    }
}
