package com.binarfood.binarfoodfcmservice.service;

import com.binarfood.binarfoodfcmservice.dto.fcm.request.NotificationRequest;

import java.util.concurrent.ExecutionException;

public interface FCMService {
    void sendMessageToToken(NotificationRequest request)
            throws InterruptedException, ExecutionException;

}
