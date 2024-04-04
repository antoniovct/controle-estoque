package br.com.antonio_victor.controle_estoque.dto;

import br.com.antonio_victor.controle_estoque.model.Produto;

import java.util.List;

public record DadosRelatorioEstoqueDto(
        Integer totalProdutos,
        Double valorTotalEstoque,
        List<Produto> produtosEstoqueBaixo
) {
}
