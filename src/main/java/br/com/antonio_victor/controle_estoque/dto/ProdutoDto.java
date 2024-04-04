package br.com.antonio_victor.controle_estoque.dto;

import br.com.antonio_victor.controle_estoque.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoDto(
        @NotBlank(message = "A descrição do produto não pode estar em branco!")
        String descricao,
        @NotNull(message = "O preço de custo não pode estar em branco!")
        @Positive(message = "O preço de custo deve ser um número positivo!")
        Double precoCusto,
        @NotNull(message = "O preço de venda não pode estar em branco!")
        @Positive(message = "O preço de venda deve ser um número positivo!")
        Double precoVenda,
        @NotNull(message = "A quantidade de estoque não pode estar em branco!")
        @Positive(message = "A quantidade de estoque deve ser um número positivo!")
        Integer quantidadeEstoque,
        @NotNull(message = "A categoria do produto não pode estar em branco!")
        Categoria categoria
) {
}
