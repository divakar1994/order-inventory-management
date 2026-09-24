package com.ordermanage.inventory.inventory.exception;

public class DuplicateInventoryException extends RuntimeException{

    public DuplicateInventoryException(Long productId){
        super("Inventory with productId: " + productId + "' already exists");
    }
}
