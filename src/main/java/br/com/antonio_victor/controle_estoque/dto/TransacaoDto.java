package br.com.antonio_victor.controle_estoque.dto;

import br.com.antonio_victor.controle_estoque.model.TipoTransacao;

public record TransacaoDto(
        Integer quantidade,
        TipoTransacao tipoTransacao
) {
}
