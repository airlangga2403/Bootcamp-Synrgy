package com.binarfud.proplayer.challange5.dto.merchant.response;

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

    public AddMerchantResponseDTO() {

    }



}
