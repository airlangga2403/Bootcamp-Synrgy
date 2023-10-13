package com.example.synrgy6.service;

import com.example.synrgy6.model.Merchants;
import com.example.synrgy6.model.Orders;
import com.example.synrgy6.repository.MerchantsRepository;
import com.example.synrgy6.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantsService {
    @Autowired
    private MerchantsRepository merchantsRepository;

    public Merchants createMerchant(Merchants merchants) {
        return merchantsRepository.save(merchants);
    }
}
