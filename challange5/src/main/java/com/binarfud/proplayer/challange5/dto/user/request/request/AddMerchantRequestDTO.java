package com.binarfud.proplayer.challange5.dto.merchant.request;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddMerchantRequestDTO {
    private UUID userId;
    private String merchantName;
    private String merchantLocation;
}
