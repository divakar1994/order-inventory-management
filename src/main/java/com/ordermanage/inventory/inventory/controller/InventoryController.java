package com.ordermanage.inventory.inventory.controller;

import com.ordermanage.inventory.inventory.dto.CreateInventoryRequest;
import com.ordermanage.inventory.inventory.dto.InventoryResponse;
import com.ordermanage.inventory.inventory.dto.UpdateInventoryRequest;
import com.ordermanage.inventory.inventory.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }
    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(@Valid @RequestBody CreateInventoryRequest request){
        return new ResponseEntity<>(inventoryService.createInventory(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Long id){
        return new ResponseEntity<>(inventoryService.getInventoryById(id), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(@PathVariable Long productId){
        return new ResponseEntity<>(inventoryService.getInventoryByProductId(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventories(){
        return new ResponseEntity<>(inventoryService.getAllInventories(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(@Valid @RequestBody UpdateInventoryRequest request, @PathVariable Long id){
        return new ResponseEntity<>(inventoryService.updateInventory(id,request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id){
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}
