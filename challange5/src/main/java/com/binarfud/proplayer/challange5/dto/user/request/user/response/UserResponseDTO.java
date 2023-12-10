package com.binarfud.proplayer.challange5.dto.user.request.user.response;

import com.binarfud.proplayer.challange5.dto.user.request.user.request.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private UUID uuid;
    private String message;

    public UserResponseDTO(UserRequestDTO user, String message) {
        if (user != null) {
            this.uuid = user.getUuid();
        } else {
            this.uuid = null;
        }
        this.message = message;
    }



}
