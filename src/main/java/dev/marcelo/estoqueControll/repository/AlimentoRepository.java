package dev.marcelo.estoqueControll.repository;

import dev.marcelo.estoqueControll.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlimentoRepository extends JpaRepository<Alimento,Long> {
    @Query(value = "SELECT * FROM alimento a " +
            "WHERE a.nome = :nome " +
            "AND a.deposito_id IS NOT NULL " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<Alimento> findFirstDisponivel(@Param("nome") String nome);
}
