package com.binarfud.proplayer.challange5.dto.merchant.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMerchantRequestDTO {
    private String merchantName;
    private String merchantLocation;
    private Boolean open;
}
