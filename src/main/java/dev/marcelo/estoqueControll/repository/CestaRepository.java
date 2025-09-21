package dev.marcelo.estoqueControll.repository;

import dev.marcelo.estoqueControll.model.Cesta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CestaRepository extends JpaRepository<Cesta,Long>  {
}
