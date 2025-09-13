package dev.marcelo.estoqueControll.repository;

import dev.marcelo.estoqueControll.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepository extends JpaRepository<Alimento,Long> {
}
