package com.org.challange4.controllers;

import com.org.challange4.dto.merchant.request.AddMerchantRequestDTO;
import com.org.challange4.dto.merchant.request.UpdateMerchantRequestDTO;
import com.org.challange4.dto.merchant.response.AddMerchantResponseDTO;
import com.org.challange4.dto.merchant.response.UpdateMerchantResponseDTO;
import com.org.challange4.models.Merchants;
import com.org.challange4.services.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping("/add")
    public AddMerchantResponseDTO addMerchant(@RequestBody AddMerchantRequestDTO requestDTO) {
        log.info("Received a request to add a merchant.");
        AddMerchantResponseDTO responseDTO = merchantService.addMerchant(requestDTO);
        log.info("Response from addMerchant: {}", responseDTO);
        return responseDTO;
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> updateMerchant(@PathVariable UUID id, @RequestBody UpdateMerchantRequestDTO updateMerchantDTO) {
        log.info("Received a request to update a merchant with ID: {}", id);
        UpdateMerchantResponseDTO updatedMerchant = merchantService.updateMerchant(id, updateMerchantDTO);
        log.info("Response from updateMerchant: {}", updatedMerchant);
        if (updatedMerchant != null) {
            return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
        } else {
            log.error("Unable to update merchant with ID: {}", id);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("uid", id);
            errorResponse.put("message", "UID tidak valid");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getOpenMerchant() {
        log.info("Received a request to get open merchants.");
        List<Merchants> merchants = merchantService.getMerchantOpen();
        if (merchants != null) {
            log.info("Response from getOpenMerchant: {}", merchants);
            return new ResponseEntity<>(merchants, HttpStatus.OK);
        } else {
            log.error("No open merchants found.");
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "tidak ada merchants yang open");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
