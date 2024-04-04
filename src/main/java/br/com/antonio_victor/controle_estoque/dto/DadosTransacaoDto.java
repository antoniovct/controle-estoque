package br.com.antonio_victor.controle_estoque.dto;

import br.com.antonio_victor.controle_estoque.model.TipoTransacao;
import br.com.antonio_victor.controle_estoque.model.Transacao;

public record DadosTransacaoDto(
        Long id,
        String data,
        Long codigoProduto,
        Integer quantidade,
        TipoTransacao tipoTransacao
) {
    public DadosTransacaoDto(Transacao transacao) {
        this(transacao.getId(), transacao.getData(), transacao.getProduto().getCodigo(),transacao.getQuantidade(), transacao.getTipoTransacao());
    }
}
