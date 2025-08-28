package br.com.carlos.usuario;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        usuario.setId((long) (usuarios.size() + 1)); // gera ID simples
        usuarios.add(usuario);
        return usuario;
    }

    @GetMapping("/hello")
    public String HelloWord(){
        return "Hello World!";
    }
}
