package com.ordermanage.inventory.inventory.exception;

public class InventoryNotFoundException extends RuntimeException{

    public InventoryNotFoundException(Long id){
        super("Inventory with ID:"+id+" was not found");
    }
}
