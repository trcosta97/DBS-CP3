package com.dbs.cp3.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record atualizarProdutoDTO(
        @NotNull
        @Min(0)
        BigDecimal preco
) {
}