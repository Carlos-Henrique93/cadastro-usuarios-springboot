package br.com.carlos.usuario;


import br.com.carlos.usuario.UsuarioRepository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


    @RestController
    @RequestMapping("/usuarios") // ← atenção aqui
    public class UsuarioController {

        private final UsuarioRepository usuarioRepository;

        public UsuarioController(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        @PostMapping
        public Usuario criarUsuario(@RequestBody Usuario usuario) {
            return usuarioRepository.save(usuario);
        }

}
