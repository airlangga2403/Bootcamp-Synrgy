package com.binarfud.proplayer.challange5.services;

import com.binarfud.proplayer.challange5.dto.product.request.AddProductRequestDTO;
import com.binarfud.proplayer.challange5.dto.product.request.UpdateProductRequestDTO;
import com.binarfud.proplayer.challange5.dto.product.response.UpdateProductResponseDTO;
import com.binarfud.proplayer.challange5.models.Merchants;
import com.binarfud.proplayer.challange5.models.Products;
import com.binarfud.proplayer.challange5.repository.MerchantRepository;
import com.binarfud.proplayer.challange5.repository.ProductRepository;
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

    // UPDATE PRODUCT BY ID MERCHANT
    // REQUEST => [ ID MERCHANT, ID PRODUCT, merchantStoreName, merchantLocation ]
    public UpdateProductResponseDTO updateProduct(UUID idMerchant, UUID idProduct, UpdateProductRequestDTO updateProductRequestDTO) {
        Optional<Merchants> merchants = merchantRepository.findById(idMerchant);
        Optional<Products> product = productRepository.findById(idProduct);

        if (merchants.isPresent() && product.isPresent()) {
            productRepository.updateProductNameById(idProduct,
                    updateProductRequestDTO.getProductName());

            UpdateProductResponseDTO updateProductResponseDTO = new UpdateProductResponseDTO();
            updateProductResponseDTO.setProductId(idProduct);
            updateProductResponseDTO.setMerchantId(idMerchant);
            updateProductResponseDTO.setMessage("Success updating product data");
            return updateProductResponseDTO;
        }

        return null;
    }


}
