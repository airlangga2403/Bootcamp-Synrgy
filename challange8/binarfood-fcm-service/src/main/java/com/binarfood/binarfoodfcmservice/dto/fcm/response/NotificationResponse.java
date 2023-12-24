package com.binarfood.binarfoodfcmservice.dto.fcm.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {
    private int status;
    private String message;
}
