package com.example.synrgy6.controller;

import com.example.synrgy6.model.Merchants;
import com.example.synrgy6.model.Products;
import com.example.synrgy6.service.MerchantsService;
import com.example.synrgy6.service.ProductService;
import com.example.synrgy6.view.BinarFudView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class ProductController {
    private final ProductService productService;
    private final MerchantsService merchantsService; // Inject the MerchantsService
    private final BinarFudView view = new BinarFudView();

    @Autowired
    public ProductController(ProductService productService, MerchantsService merchantsService) {
        this.productService = productService;
        this.merchantsService = merchantsService; // Initialize the MerchantsService
    }

    public void initProduct() {
        List<Products> products = new ArrayList<>();

        // get Merchant And Random
        List<Merchants> merchants = merchantsService.getMerchant();

        Random random = new Random();
        int randomIndex = random.nextInt(merchants.size());
        Merchants merchant = merchants.get(randomIndex);

        products.add(createProduct("Nasi Goreng", 15.0000, merchant));
        products.add(createProduct("Mie Ayam", 12.000, merchant));
        products.add(createProduct("Sate Ayam", 10.000, merchant));

        productService.saveProducts(products);
    }


    private Products createProduct(String productName, Double price, Merchants merchants) {
        Products product = new Products();
        product.setProductName(productName);
        product.setMerchant(merchants);
        product.setPrice(price);
        return product;
    }


}
