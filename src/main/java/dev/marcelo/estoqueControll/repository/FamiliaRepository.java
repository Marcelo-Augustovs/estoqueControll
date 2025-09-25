package dev.marcelo.estoqueControll.repository;

import dev.marcelo.estoqueControll.model.Familia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaRepository extends JpaRepository<Familia,Long> {

    Familia findByNome(String nome);
}
