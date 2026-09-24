package com.ordermanage.inventory.product.controller;

import com.ordermanage.inventory.product.dto.CreateProductRequest;
import com.ordermanage.inventory.product.dto.ProductResponse;
import com.ordermanage.inventory.product.dto.UpdateProductRequest;
import com.ordermanage.inventory.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request){
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductResponse> getProductBySku(@PathVariable String sku){
        return new ResponseEntity<>(productService.getProductBySku(sku), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody UpdateProductRequest request, @PathVariable Long id){
        return new ResponseEntity<>(productService.updateProduct(id,request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deactivateProduct(@PathVariable Long id){
        productService.deactivateProduct(id);
        return ResponseEntity.noContent().build();
    }
}
