package com.org.challange4.services;

import com.org.challange4.dto.product.response.AddProductRequestDTO;
import com.org.challange4.models.Merchants;
import com.org.challange4.models.Products;
import com.org.challange4.repository.MerchantRepository;
import com.org.challange4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MerchantRepository merchantRepository;

    public Products addProductByMerchantId(UUID merchantId, AddProductRequestDTO productDTO) {
        Optional<Merchants> merchant = merchantRepository.findById(merchantId);
        if (merchant.isPresent()) {
            Products product = new Products();
            product.setProductName(productDTO.getProductName());
            product.setPrice(productDTO.getPrice());
            product.setMerchant(merchant.get());
            return productRepository.save(product);
        }
        return null;
    }

    public Boolean deleteProductById(UUID productID) {
        Optional<Products> products = productRepository.findById(productID);
        if (products.isPresent()) {
            productRepository.deleteById(productID);
            return true;
        } else {
            return false;
        }
    }

    public List<Products> getAllProducts(Integer offset, Integer size) {
        Sort sort = Sort.unsorted();
        PageRequest pageRequest = PageRequest.of(offset / size, size, sort);
        Slice<Products> productsSlice = productRepository.findAll(pageRequest);
        return productsSlice.getContent();
    }

}
