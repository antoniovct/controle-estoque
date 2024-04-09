package br.com.antonio_victor.controle_estoque.service;

import br.com.antonio_victor.controle_estoque.dto.DadosUsuarioDto;
import br.com.antonio_victor.controle_estoque.dto.UsuarioDto;
import br.com.antonio_victor.controle_estoque.model.Usuario;
import br.com.antonio_victor.controle_estoque.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosUsuarioDto cadastrar(UsuarioDto usuarioDto) {
        String senhaHash = BCrypt.hashpw(usuarioDto.senha(), BCrypt.gensalt());
        Usuario usuario = new Usuario(usuarioDto.usuario(), senhaHash, usuarioDto.tipoUsuario());
        usuarioRepository.save(usuario);
        return new DadosUsuarioDto(usuario);
    }
}
