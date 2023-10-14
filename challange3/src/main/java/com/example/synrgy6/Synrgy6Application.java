package com.example.synrgy6;

import com.example.synrgy6.controller.MerchantController;
import com.example.synrgy6.controller.OrdersController;
import com.example.synrgy6.controller.ProductController;
import com.example.synrgy6.controller.UsersController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Synrgy6Application implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Synrgy6Application.class, args);
        UsersController usersController = context.getBean(UsersController.class);
        MerchantController merchantController = context.getBean(MerchantController.class);
        ProductController productController = context.getBean(ProductController.class);
        OrdersController ordersController = context.getBean(OrdersController.class);

//        merchantController.initProduct();
//        productController.initProduct();
        usersController.welcomeUser();
        ordersController.addOrder();

    }

    @Override
    public void run(String... args) throws Exception {
        // Code to be executed when the application starts
        System.out.println("Application started! Performing startup tasks...");
    }


}
