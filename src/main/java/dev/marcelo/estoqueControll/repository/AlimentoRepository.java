package dev.marcelo.estoqueControll.repository;

import dev.marcelo.estoqueControll.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlimentoRepository extends JpaRepository<Alimento,Long> {
    Optional<Alimento> findFirstByNome(String nomeDoAlimento);
}
