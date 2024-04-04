package br.com.antonio_victor.controle_estoque.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EntradaProdutoDto(
        @NotNull(message = "É necessário informar o código do produto.")
        Long codigo,
        @NotNull(message = "É necessário informar a quantidade que será acrescentada ao produto.")
        @Positive(message = "A quantidade precisa ser um número positivo.")
        Integer quantidade

) {
}
