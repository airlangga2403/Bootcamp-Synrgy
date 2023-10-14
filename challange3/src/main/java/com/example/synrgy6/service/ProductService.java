package com.example.synrgy6.service;

import com.example.synrgy6.model.Products;
import com.example.synrgy6.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void saveProducts(List<Products> products) {
        productRepository.saveAll(products);
    }

    public List<Products> getProduct() {
        return productRepository.findAll();
    }

    public Products getProductByReferenceId(Long referenceId) {
        return productRepository.getById(referenceId);
    }


}
