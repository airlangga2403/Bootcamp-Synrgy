package com.org.challange4.controllers;

import com.org.challange4.dto.product.response.AddProductRequestDTO;
import com.org.challange4.models.Products;
import com.org.challange4.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add/{id}")
    public ResponseEntity<?> addProductById(@PathVariable UUID id, @RequestBody AddProductRequestDTO addProductRequestDTO) {
        log.info("Received a request to add a product for merchant with ID: {}", id);
        Products newProduct = productService.addProductByMerchantId(id, addProductRequestDTO);
        if (newProduct != null) {
            log.info("Response from addProductById: {}", newProduct);
            return new ResponseEntity<>(newProduct, HttpStatus.OK);
        } else {
            log.error("Unable to add product for merchant with ID: {}", id);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("uid", id);
            errorResponse.put("message", "UID tidak valid");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable UUID id) {
        log.info("Received a request to delete a product with ID: {}", id);
        Boolean deleteProduct = productService.deleteProductById(id);
        if (deleteProduct) {
            log.info("Product deleted successfully with ID: {}", id);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("uid", id);
            errorResponse.put("message", "Product deleted successfully");
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        } else {
            log.error("Product not found or unable to delete with ID: {}", id);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("uid", id);
            errorResponse.put("message", "Product not found or unable to delete");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size) {
        log.info("Received a request to get all products.");
        Integer offset = (page - 1) * size;
        List<Products> listProducts = productService.getAllProducts(offset, size);

        if (listProducts != null) {
            log.info("Response from getAllProduct: {}", listProducts);
            return new ResponseEntity<>(listProducts, HttpStatus.OK);
        } else {
            log.error("Products not found.");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Product not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}