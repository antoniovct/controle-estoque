package br.com.antonio_victor.controle_estoque.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemPedidoDto(
        @NotNull(message = "A quantidade não pode estar vazia!")
        @Positive(message = "A quantidade deve ser um número positivo!")
        Integer quantidade
) {
}
