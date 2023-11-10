package com.binarfud.proplayer.challange5.dto.order.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseDTO {
    private UUID id;
    private UUID productId;
    private Long quantity;
    private Double totalPrice;
}
