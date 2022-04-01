package com.ljt.study.socketio.client;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.VoidAckCallback;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2022-04-01 15:49
 */
@Slf4j
class AckServer extends AbstractServer {

    public static void main(String[] args) {
        AckServer ackServer = new AckServer();

        ackServer.getServer().addEventListener("ack.event.1", ChatMessage.class, (client, data, ackRequest) -> {
            // check is ack requested by client,
            // but it's not required check
            System.out.println("isAckRequested - " + ackRequest.isAckRequested());
            if (ackRequest.isAckRequested()) {
                // send ack response with data to client
                ackRequest.sendAckData("client message was delivered to server!", "yeah!");
            }

            // send message back to client with ack callback WITH data
            ChatMessage msg1 = new ChatMessage(data.getUserName(), "message with ack data");
            client.sendEvent("ack.event.2", new AckCallback<String>(String.class) {
                @Override
                public void onSuccess(String result) {
                    log.info("ack from client: {}", result);
                }
            }, msg1);

            ChatMessage msg2 = new ChatMessage(data.getUserName(), "message with void ack");
            client.sendEvent("ack.event.3", new VoidAckCallback() {
                @Override
                protected void onSuccess() {
                    log.info("void ack from: {}", client.getSessionId());
                }

            }, msg2);
        });

        ackServer.start();
    }

}
