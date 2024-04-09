package br.com.antonio_victor.controle_estoque.dto;

import br.com.antonio_victor.controle_estoque.model.TipoUsuario;

public record UsuarioDto(String usuario, String senha, TipoUsuario tipoUsuario) {

}
