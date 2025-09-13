package dev.marcelo.estoqueControll.controller;

import dev.marcelo.estoqueControll.dto.LoginTokenDto;
import dev.marcelo.estoqueControll.dto.UsuarioRegisterDto;
import dev.marcelo.estoqueControll.infra.exceptions.PasswordInvalidException;
import dev.marcelo.estoqueControll.infra.exceptions.UsernameUniqueViolationException;
import dev.marcelo.estoqueControll.infra.jwt.TokenService;
import dev.marcelo.estoqueControll.model.Usuario;
import dev.marcelo.estoqueControll.repository.UsuarioRepository;
import dev.marcelo.estoqueControll.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuario")
public class UsuarioController {


   private final UsuarioService usuarioService;
   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
   private UsuarioRepository repository;
   @Autowired
   private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity Register(@RequestBody @Valid UsuarioRegisterDto data){

        String encyptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario newUsuario = new Usuario(data.login(),encyptedPassword);

        try {
            this.repository.save(newUsuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String.format("Username %s já foi cadastrado", newUsuario.getUsername()));
        }

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioRegisterDto data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken( (Usuario) auth.getPrincipal());

            return ResponseEntity.ok(new LoginTokenDto(token));
        } catch (AuthenticationException e) {
            throw new PasswordInvalidException(e.getMessage());
        }
    }
}
