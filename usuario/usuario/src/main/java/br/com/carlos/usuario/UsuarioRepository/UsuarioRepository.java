package br.com.carlos.usuario.UsuarioRepository;


import br.com.carlos.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
