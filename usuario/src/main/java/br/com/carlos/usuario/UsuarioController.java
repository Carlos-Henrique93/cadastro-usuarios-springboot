package br.com.carlos.usuario;


import br.com.carlos.usuario.UsuarioRepository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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


        //  Listar todos os usuários
        @GetMapping ("/lista")
        public List<Usuario> listarUsuarios() {
            return usuarioRepository.findAll();
        }

        @GetMapping("/lista/{id}")
        public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
            return usuarioRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }


    }
