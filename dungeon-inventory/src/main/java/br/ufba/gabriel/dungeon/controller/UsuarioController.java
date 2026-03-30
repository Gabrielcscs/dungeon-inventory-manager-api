package br.ufba.gabriel.dungeon.controller;

import br.ufba.gabriel.dungeon.model.Usuario;
import br.ufba.gabriel.dungeon.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins= "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastrar")
    public Usuario cadastrar(@RequestBody Usuario novoUsuario){
        Usuario usuarioDoBanco = usuarioRepository.findByUsername(novoUsuario.getUsername());
        if(usuarioDoBanco != null) {
            return null;
        }else{
            novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
            return usuarioRepository.save(novoUsuario);
        }
    }

    @PostMapping("/login")
    public Usuario logar(@RequestBody Usuario loginUsuario){
        Usuario usuarioDoBanco = usuarioRepository.findByUsername(loginUsuario.getUsername());
        if(usuarioDoBanco != null && passwordEncoder.matches(loginUsuario.getSenha(), usuarioDoBanco.getSenha())){
            return usuarioDoBanco;
        }else {return null;}
    }
}
