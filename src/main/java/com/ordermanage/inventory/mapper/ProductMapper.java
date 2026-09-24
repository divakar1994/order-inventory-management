package com.ordermanage.inventory.mapper;

import com.ordermanage.inventory.product.dto.CreateProductRequest;
import com.ordermanage.inventory.product.dto.ProductResponse;
import com.ordermanage.inventory.product.dto.UpdateProductRequest;
import com.ordermanage.inventory.product.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setSku(request.sku());
        return product;
    }
    public void populate(UpdateProductRequest request, Product product) {
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setSku(request.sku());
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getSku(), product.getName(), product.getDescription(),
                product.getPrice(), product.isActive(), product.getCreatedAt(), product.getUpdatedAt());
    }
}

