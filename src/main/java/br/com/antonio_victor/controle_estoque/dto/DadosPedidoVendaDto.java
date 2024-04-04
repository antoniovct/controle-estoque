package br.com.antonio_victor.controle_estoque.dto;

import br.com.antonio_victor.controle_estoque.model.PedidoVenda;

import java.util.List;

public record DadosPedidoVendaDto(
        Long numero,
        String data,
        List<DadosItemPedido> produtos,
        Double valorTotal
) {
    public DadosPedidoVendaDto(PedidoVenda pedidoVenda) {
        this(pedidoVenda.getNumero(), pedidoVenda.getData(),
                pedidoVenda.getProdutos().stream().map(DadosItemPedido::new).toList(),
                pedidoVenda.getValorTotal());
    }
}
