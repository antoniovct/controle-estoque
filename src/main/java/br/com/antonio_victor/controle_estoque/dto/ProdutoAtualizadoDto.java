package br.com.antonio_victor.controle_estoque.dto;

import jakarta.validation.constraints.Positive;

public record ProdutoAtualizadoDto(
        String descricao,
        @Positive(message = "O preço de custo deve ser um número positivo!")
        Double precoCusto,
        @Positive(message = "O preço de venda deve ser um número positivo!")
        Double precoVenda,
        @Positive(message = "A quantidade de estoque deve ser um número positivo!")
        Integer quantidadeEstoque
) {
}
