package br.com.antonio_victor.controle_estoque.dto;

import br.com.antonio_victor.controle_estoque.model.ItemPedido;

public record DadosItemPedido(
        Long id,
        Long codigoProduto,
        String descricao,
        Integer quantidade,
        Double valorUnitario,
        Double valorTotal
) {
    public DadosItemPedido(ItemPedido itemPedido) {
        this(itemPedido.getId(), itemPedido.getProduto().getCodigo(), itemPedido.getProduto().getDescricao(), itemPedido.getQuantidade(), itemPedido.getValorUnitario(), itemPedido.getValorTotal());
    }
}
