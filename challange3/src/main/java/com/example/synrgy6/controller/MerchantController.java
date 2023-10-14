package com.example.synrgy6.controller;

import com.example.synrgy6.model.Merchants;
import com.example.synrgy6.service.MerchantsService;
import com.example.synrgy6.view.BinarFudView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class MerchantController {
    private final MerchantsService merchantsService;

    private final BinarFudView view = new BinarFudView();
    private static final Scanner inpScanner = new Scanner(System.in);

    @Autowired
    public MerchantController(MerchantsService merchantService) {
        this.merchantsService = merchantService;
    }

    public void initProduct() {
        addProduct("Mang Asep", "Bandung", true);
    }

    private void addProduct(String merchantName, String merchantLocation, Boolean open) {
        Merchants merchants = new Merchants();
        merchants.setMerchantName(merchantName);
        merchants.setMerchantLocation(merchantLocation);
        merchants.setOpen(open);

        merchantsService.createMerchant(merchants);
    }
}
