package com.binarfood.chat.WebSocket.Binarchat.websocket;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SocketModule {

    private final SocketIOServer server;

    public SocketModule(SocketIOServer server) {
        this.server = server;

        server.addConnectListener(onConnected);
        server.addDisconnectListener(onDisconnected);
        server.addEventListener("chat", MessageModel.class, onMessageSent);
    }

    private ConnectListener onConnected = socketIOClient -> log.info("User Connected");
    private DisconnectListener onDisconnected = socketIOClient -> log.info("User Disconnected");

    private final DataListener<MessageModel> onMessageSent = new DataListener<>() {
        @Override
        public void onData(SocketIOClient socketIOClient, MessageModel s, AckRequest ackRequest) throws Exception {
            // todo
            log.info("Pesan dari : " + s.getFrom() + "ke : " + s.getTo() + "pesan : " + s.getMsgBody());
            server.getBroadcastOperations()
                    .sendEvent(s.getTo(), socketIOClient, s.getMsgBody()); // mengirim message
            ackRequest.sendAckData("Pesan sudah terkirim ke " + s.getTo());
        }
    };


}
