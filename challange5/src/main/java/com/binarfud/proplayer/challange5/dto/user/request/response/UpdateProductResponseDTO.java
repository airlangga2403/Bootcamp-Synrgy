package com.binarfud.proplayer.challange5.dto.user.request.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateProductResponseDTO {
    private UUID merchantId;
    private UUID productId;
    private String message;
}
