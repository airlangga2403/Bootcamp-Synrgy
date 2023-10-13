package com.example.synrgy6.controller;

import com.example.synrgy6.service.OrdersService;
import com.example.synrgy6.view.BinarFudView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class OrdersController {
    private final OrdersService ordersService;
    private final BinarFudView view = new BinarFudView();
    private static final Scanner inpScanner = new Scanner(System.in);

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    public void addOrder() {

    }

}
