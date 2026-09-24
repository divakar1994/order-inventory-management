package com.ordermanage.inventory.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateProductRequest(

        @NotBlank
        @Size(max = 50)
        String sku,

        @NotBlank
        @Size(max = 200)
        String name,

        @Size(max = 1000)
        String description,

        @NotNull
        @Positive
        @Digits(integer = 10, fraction = 2)
        BigDecimal price
) {
}
