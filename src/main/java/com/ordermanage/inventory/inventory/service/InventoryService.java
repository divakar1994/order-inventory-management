package com.ordermanage.inventory.inventory.service;

import com.ordermanage.inventory.inventory.dto.CreateInventoryRequest;
import com.ordermanage.inventory.inventory.dto.InventoryResponse;
import com.ordermanage.inventory.inventory.dto.UpdateInventoryRequest;
import com.ordermanage.inventory.inventory.entity.Inventory;
import com.ordermanage.inventory.inventory.exception.DuplicateInventoryException;
import com.ordermanage.inventory.inventory.exception.InventoryNotFoundException;
import com.ordermanage.inventory.inventory.repository.InventoryRepository;
import com.ordermanage.inventory.mapper.InventoryMapper;
import com.ordermanage.inventory.product.entity.Product;
import com.ordermanage.inventory.product.exception.ProductNotFoundException;
import com.ordermanage.inventory.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository, InventoryRepository inventoryRepository, InventoryMapper inventoryMapper){
        this.inventoryMapper = inventoryMapper;
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public InventoryResponse createInventory(CreateInventoryRequest request) {

        Product product = productRepository.findById(request.productId()).orElseThrow(() -> new ProductNotFoundException(request.productId()));

        if(inventoryRepository.existsByProductId(request.productId())){
            throw new DuplicateInventoryException(request.productId());
        }
        Inventory inventory = inventoryMapper.toEntity(request,product);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return inventoryMapper.toResponse(savedInventory);
    }

    @Transactional(readOnly = true)
    public InventoryResponse getInventoryById(Long id){
        Inventory searchInventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(id));
        return inventoryMapper.toResponse(searchInventory);

    }

    @Transactional(readOnly = true)
    public InventoryResponse getInventoryByProductId(Long productId){
        Inventory searchInventory = inventoryRepository.findByProductId(productId).orElseThrow(() -> new InventoryNotFoundException(productId));
        return inventoryMapper.toResponse(searchInventory);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> getAllInventories(){
        List<Inventory> allInventories = inventoryRepository.findAll();
        List<InventoryResponse> result = new ArrayList<>();

        allInventories.forEach(inventory -> {
            result.add(inventoryMapper.toResponse(inventory));

        });

        return result;
    }

    @Transactional
    public InventoryResponse updateInventory(Long id, UpdateInventoryRequest request) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(id));
        inventoryMapper.populate(request,inventory);
        return inventoryMapper.toResponse(inventory);
    }

    @Transactional
    public void deleteInventory(Long id){
        Inventory searchInventory = inventoryRepository.findById(id).orElseThrow(() -> new InventoryNotFoundException(id));
        inventoryRepository.delete(searchInventory);
    }
}
