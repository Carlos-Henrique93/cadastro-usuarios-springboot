package br.com.carlos.usuario;


import br.com.carlos.usuario.UsuarioRepository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @CrossOrigin(origins = "http://localhost:3000") // <-- React rodando no localhost:3000
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

        // DELETE http://localhost:8080/usuarios/{id}
        @DeleteMapping("/deletar/{id}")
        public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
                return ResponseEntity.ok("Usuário com ID " + id + " foi deletado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuário com ID " + id + " não encontrado.");
            }
        }
        // Atualizar por ID
        @PutMapping("/atualiza/{id}")
        public ResponseEntity<Usuario> atualizarPorId(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
            return usuarioRepository.findById(id)
                    .map(usuario -> {
                        usuario.setNome(usuarioAtualizado.getNome());
                        usuario.setCpf(usuarioAtualizado.getCpf());
                        usuario.setTelefone(usuarioAtualizado.getTelefone());
                        usuario.setEmail(usuarioAtualizado.getEmail());
                        usuario.setSenha(usuarioAtualizado.getSenha());
                        usuario.setAtivo(usuarioAtualizado.isAtivo());
                        return ResponseEntity.ok(usuarioRepository.save(usuario));
                    })
                    .orElse(ResponseEntity.notFound().build());
        }
    }
