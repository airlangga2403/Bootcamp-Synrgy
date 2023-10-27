package com.org.challange4.dto.order.response;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponseDTO {
    private UUID id;
    private Date orderTime;
    private String destinationAddress;
    private List<OrderDetailResponseDTO> orderDetails;
    private boolean completed;
}
