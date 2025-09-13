package dev.marcelo.estoqueControll.repository;

import dev.marcelo.estoqueControll.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

   Usuario findByLogin(String username);
}
