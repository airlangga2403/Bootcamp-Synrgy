package com.org.challange4.dto.product.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequestDTO {
    private String productName;
    private Double price;
}
