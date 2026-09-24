package com.ordermanage.inventory.mapper;

import com.ordermanage.inventory.inventory.dto.CreateInventoryRequest;
import com.ordermanage.inventory.inventory.dto.InventoryResponse;
import com.ordermanage.inventory.inventory.dto.UpdateInventoryRequest;
import com.ordermanage.inventory.inventory.entity.Inventory;
import com.ordermanage.inventory.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    private final ProductMapper productMapper;

    public InventoryMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public Inventory toEntity(CreateInventoryRequest request, Product product) {
        Inventory inventory = new Inventory();
        inventory.setQuantity(request.quantity());
        inventory.setProduct(product);
        return inventory;
    }

    public void populate(UpdateInventoryRequest request, Inventory inventory) {
        inventory.setQuantity(request.quantity());
    }

    public InventoryResponse toResponse(Inventory inventory) {
        return new InventoryResponse(inventory.getId(), productMapper.toResponse(inventory.getProduct()),
                inventory.getQuantity(), inventory.getCreatedAt(), inventory.getUpdatedAt());
    }
}
