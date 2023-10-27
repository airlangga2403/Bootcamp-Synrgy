package com.org.challange4.dto.order.response;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
