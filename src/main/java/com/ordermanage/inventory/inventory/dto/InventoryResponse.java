package com.ordermanage.inventory.inventory.dto;

import com.ordermanage.inventory.product.dto.ProductResponse;

import java.time.LocalDateTime;

public record InventoryResponse(

        Long id,
        ProductResponse product,
        Integer quantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
