package dev.marcelo.estoqueControll.repository;

import dev.marcelo.estoqueControll.model.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepositoRepository extends JpaRepository<Deposito,Long> {
    Optional<Deposito> findByMesAndAno(int mes, int ano);
}
