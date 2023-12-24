package com.binarfood.chat.WebSocket.Binarchat.websocket;

import lombok.Data;

@Data
public class MessageModel {
    private String from;
    private String to;
    private String msgBody;
}
