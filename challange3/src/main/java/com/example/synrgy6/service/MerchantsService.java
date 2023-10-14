package com.example.synrgy6.service;

import com.example.synrgy6.model.Merchants;
import com.example.synrgy6.repository.MerchantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantsService {
    @Autowired
    private MerchantsRepository merchantsRepository;

    public Merchants createMerchant(Merchants merchants) {
        return merchantsRepository.save(merchants);
    }


    public List<Merchants> getMerchant(){
        return merchantsRepository.findAll();
    }
}
