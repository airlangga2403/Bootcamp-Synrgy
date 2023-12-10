package com.binarfud.proplayer.challange5.dto.user.request.user.response;

import com.binarfud.proplayer.challange5.dto.user.request.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseListDTO {
    private UUID id;
    private String username;
    private String emailAddress;

    private List<Orders> orders;
}

