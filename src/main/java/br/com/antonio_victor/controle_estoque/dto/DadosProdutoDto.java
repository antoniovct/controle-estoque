package br.com.antonio_victor.controle_estoque.dto;

import br.com.antonio_victor.controle_estoque.model.Categoria;
import br.com.antonio_victor.controle_estoque.model.Produto;

public record DadosProdutoDto(
        Long codigo,
        String descricao,
        Double precoCusto,
        Double precoVenda,
        Integer quantidadeEstoque,
        Categoria categoria
) {
    public DadosProdutoDto(Produto produto) {
        this(produto.getCodigo(), produto.getDescricao(), produto.getPrecoCusto(),
                produto.getPrecoVenda(), produto.getQuantidadeEstoque(), produto.getCategoria());
    }
}
