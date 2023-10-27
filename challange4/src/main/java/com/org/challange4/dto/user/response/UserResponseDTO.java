package com.org.challange4.dto.user.response;

import com.org.challange4.dto.user.request.UserRequestDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String user;
    private String message;

    public UserResponseDTO(UserRequestDTO user, String message) {
        if (user != null) {
            this.user = user.getEmailAddress();
        } else {
            this.user = null;
        }
        this.message = message;
    }


}
