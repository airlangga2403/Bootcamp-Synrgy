package com.org.challange4.dto.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String emailAddress;
    private String password;
}
