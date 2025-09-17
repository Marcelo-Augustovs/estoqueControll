package dev.marcelo.estoqueControll.service;

import dev.marcelo.estoqueControll.infra.exceptions.ApiNotFoundException;
import dev.marcelo.estoqueControll.infra.exceptions.PasswordInvalidException;
import dev.marcelo.estoqueControll.model.Usuario;
import dev.marcelo.estoqueControll.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    protected Usuario save(Usuario usuario){
      return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(
                () -> new ApiNotFoundException("usuario nao encontrado")
        );
    }
    @Transactional
    public void updateSenha(Long id, String senhaAtual,String novaSenha) {
       Usuario usuario = this.findById(id);

       if(passwordEncoder.matches(senhaAtual, usuario.getSenha())){
           usuario.setSenha(passwordEncoder.encode(novaSenha));
       }else {
           throw new PasswordInvalidException("Senha do usuario incorreta");
       }
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }
}
