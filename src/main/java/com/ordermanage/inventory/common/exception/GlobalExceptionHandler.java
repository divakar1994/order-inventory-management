package com.ordermanage.inventory.common.exception;

import com.ordermanage.inventory.inventory.exception.DuplicateInventoryException;
import com.ordermanage.inventory.inventory.exception.InventoryNotFoundException;
import com.ordermanage.inventory.product.exception.DuplicateSkuException;
import com.ordermanage.inventory.product.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateSkuException.class)
    public ResponseEntity<ApiErrorResponse> duplicateSku(DuplicateSkuException exception){
        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.CONFLICT.value(),exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> validationError(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(err ->{
            errors.put(err.getField(),err.getDefaultMessage());
        });
        ValidationErrorResponse error = new ValidationErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation failed", LocalDateTime.now(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> productNotFound(ProductNotFoundException exception){
        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> inventoryNotFound(InventoryNotFoundException exception){
        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DuplicateInventoryException.class)
    public ResponseEntity<ApiErrorResponse> duplicateSku(DuplicateInventoryException exception){
        ApiErrorResponse error = new ApiErrorResponse(HttpStatus.CONFLICT.value(),exception.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
