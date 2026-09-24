package com.ordermanage.inventory.product.exception;


public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Product with ID:"+id+" was not found");
    }

    public ProductNotFoundException(String sku) {
        super("Product with SKU:"+sku+" was not found");
    }
}
