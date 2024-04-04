package br.com.antonio_victor.controle_estoque.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoAtualizadoDto(
        @NotNull(message = "O id do item não pode estar vazio!")
        Long id,
        @Positive(message = "A quantidade deve ser um número positivo!")
        Integer quantidade,
        @Positive(message = "O valor unitário deve ser um número positivo!")
        Double valorUnitario
) {
}
