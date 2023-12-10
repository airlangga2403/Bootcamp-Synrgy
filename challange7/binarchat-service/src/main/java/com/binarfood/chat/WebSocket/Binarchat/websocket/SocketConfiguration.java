package com.binarfood.chat.WebSocket.Binarchat.websocket;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketConfiguration {

    @Value("${socket.hostname}")
    private String host;

    @Value("${socket.port}")
    private int port;
    private SocketIOServer server;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration socketConfiguration = new com.corundumstudio.socketio.Configuration();
        socketConfiguration.setHostname(host);
        socketConfiguration.setPort(port);

        server = new SocketIOServer(socketConfiguration);
        server.start();

        return server;
    }

    @PreDestroy
    public void stopSocketIOServer() {
        server.start();
    }

}
