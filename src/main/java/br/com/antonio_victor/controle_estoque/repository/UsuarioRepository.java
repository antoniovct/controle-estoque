package br.com.antonio_victor.controle_estoque.repository;

import br.com.antonio_victor.controle_estoque.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByUsuario(String usuario);
}
