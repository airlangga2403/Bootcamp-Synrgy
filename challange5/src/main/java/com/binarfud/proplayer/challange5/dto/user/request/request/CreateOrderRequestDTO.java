package com.binarfud.proplayer.challange5.dto.user.request.request;

import com.binarfud.proplayer.challange5.models.Products;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CreateOrderRequestDTO {
    private String destinationAddress;
    private List<OrderDetailDTO> orderDetails;

    @Getter
    @Setter
    public static class OrderDetailDTO {
        private UUID productId;
        private Long quantity;
        @JsonIgnoreProperties("orderDetails")
        private Products product;
    }
}