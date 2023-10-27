package com.org.challange4.dto.merchant.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateMerchantResponseDTO {
    private UUID id;
    private String merchantName;
    private String merchantLocation;
    private Boolean open;

    public UpdateMerchantResponseDTO() {
    }

    public UpdateMerchantResponseDTO(UUID id, String merchantName, String merchantLocation, Boolean open) {
        this.id = id;
        this.merchantName = merchantName;
        this.merchantLocation = merchantLocation;
        this.open = open;
    }

    public UpdateMerchantResponseDTO(long id, String merchantName, String merchantLocation, Boolean open) {
        this.id = convertLongToUUID(id);
        this.merchantName = merchantName;
        this.merchantLocation = merchantLocation;
        this.open = open;
    }

    private UUID convertLongToUUID(long value) {
        return new UUID(value, 0);
    }
}
