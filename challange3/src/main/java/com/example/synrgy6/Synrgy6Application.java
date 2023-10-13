package com.example.synrgy6;

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

        usersController.welcomeUser();



    }
    @Override
    public void run(String... args) throws Exception {
        // Code to be executed when the application starts
        System.out.println("Application started! Performing startup tasks...");
    }


}
