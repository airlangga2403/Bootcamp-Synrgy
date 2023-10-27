package com.org.challange4.dto.merchant.response;

import com.org.challange4.dto.merchant.request.AddMerchantRequestDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddMerchantResponseDTO {
    private UUID id;
    private String merchantName;
    private String merchantLocation;
    private String message;

    public AddMerchantResponseDTO(UUID id, String merchantName, String merchantLocation, String message) {
        this.id = id;
        this.merchantName = merchantName;
        this.merchantLocation = merchantLocation;
        this.message = message;
    }

    public AddMerchantResponseDTO() {

    }



}
