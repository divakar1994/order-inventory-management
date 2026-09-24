package com.ordermanage.inventory.product.service;

import com.ordermanage.inventory.mapper.ProductMapper;
import com.ordermanage.inventory.product.dto.CreateProductRequest;
import com.ordermanage.inventory.product.dto.ProductResponse;
import com.ordermanage.inventory.product.dto.UpdateProductRequest;
import com.ordermanage.inventory.product.entity.Product;
import com.ordermanage.inventory.product.exception.DuplicateSkuException;
import com.ordermanage.inventory.product.exception.ProductNotFoundException;
import com.ordermanage.inventory.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {
        if(productRepository.existsBySku(request.sku())){
            throw new DuplicateSkuException(request.sku());
        }
        Product product = productMapper.toEntity(request);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id){
        Product searchProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toResponse(searchProduct);

    }

    @Transactional(readOnly = true)
    public ProductResponse getProductBySku(String sku){
        Product searchProduct = productRepository.findBySku(sku).orElseThrow(() -> new ProductNotFoundException(sku));
        return productMapper.toResponse(searchProduct);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        List<ProductResponse> result = new ArrayList<>();

        allProducts.forEach(product -> {
            result.add(productMapper.toResponse(product));

        });

        return result;
    }

    @Transactional
    public ProductResponse updateProduct(Long id,UpdateProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        Optional<Product> existingProduct = productRepository.findBySku(request.sku());
        if(existingProduct.isPresent()
        && !existingProduct.get().getId().equals(product.getId())){
            throw new DuplicateSkuException(request.sku());
        }
        productMapper.populate(request,product);
        return productMapper.toResponse(product);
    }

    @Transactional
    public void deactivateProduct(Long id){
        Product searchProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        searchProduct.setActive(false);
    }

}
