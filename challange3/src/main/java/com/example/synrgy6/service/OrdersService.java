package com.example.synrgy6.service;

import com.example.synrgy6.model.Orders;
import com.example.synrgy6.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }


    public void updateOrder(Orders order) {
        ordersRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        ordersRepository.deleteById(orderId);
    }
}
