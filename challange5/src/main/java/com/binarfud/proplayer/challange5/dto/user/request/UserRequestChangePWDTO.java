package com.binarfud.proplayer.challange5.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestChangePWDTO {
    private String username;
    private String password;
    private String newPassword;
}
