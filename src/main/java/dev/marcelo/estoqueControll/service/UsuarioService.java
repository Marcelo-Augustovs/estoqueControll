package dev.marcelo.estoqueControll.service;

import dev.marcelo.estoqueControll.model.Usuario;
import dev.marcelo.estoqueControll.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    protected Usuario save(Usuario usuario){
      return usuarioRepository.save(usuario);
    }
}
