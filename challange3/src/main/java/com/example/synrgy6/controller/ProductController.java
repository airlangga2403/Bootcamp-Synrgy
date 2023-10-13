package com.example.synrgy6.controller;

import com.example.synrgy6.service.ProductService;
import com.example.synrgy6.service.UsersService;
import com.example.synrgy6.view.BinarFudView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ProductController {
    private final ProductService productService;

    private final BinarFudView view = new BinarFudView();
    private static final Scanner inpScanner = new Scanner(System.in);

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    public void initProduct(){
//
//        productService.saveProducts()
//    }
}
