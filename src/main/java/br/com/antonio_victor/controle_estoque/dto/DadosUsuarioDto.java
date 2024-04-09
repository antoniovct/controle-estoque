package br.com.antonio_victor.controle_estoque.dto;

import br.com.antonio_victor.controle_estoque.model.TipoUsuario;
import br.com.antonio_victor.controle_estoque.model.Usuario;

public record DadosUsuarioDto(
        Long id,
        String usuario,
        String senha,
        TipoUsuario tipoUsuario
) {
    public DadosUsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getUsuario(), usuario.getSenha(), usuario.getTipoUsuario());
    }
}
