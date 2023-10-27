package com.org.challange4.controllers;

import com.org.challange4.dto.merchant.request.AddMerchantRequestDTO;
import com.org.challange4.dto.merchant.request.UpdateMerchantRequestDTO;
import com.org.challange4.dto.merchant.response.AddMerchantResponseDTO;
import com.org.challange4.dto.merchant.response.UpdateMerchantResponseDTO;
import com.org.challange4.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping("/add")
    public AddMerchantResponseDTO addMerchant(@RequestBody AddMerchantRequestDTO requestDTO) {
        AddMerchantResponseDTO responseDTO = merchantService.addMerchant(requestDTO);
        return responseDTO;
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateMerchant(@PathVariable UUID id, @RequestBody UpdateMerchantRequestDTO updateMerchantDTO) {
        try {
            UpdateMerchantResponseDTO updatedMerchant = merchantService.updateMerchant(id, updateMerchantDTO);
            if (updatedMerchant != null) {
                return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(updatedMerchant, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

}
