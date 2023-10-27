package com.org.challange4.dto.order.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.org.challange4.models.Products;
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
        private Double totalPrice;

        @JsonIgnoreProperties("orderDetails")
        private Products product;
    }
}