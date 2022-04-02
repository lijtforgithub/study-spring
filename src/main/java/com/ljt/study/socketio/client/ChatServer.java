package com.ljt.study.socketio.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author LiJingTang
 * @date 2022-04-01 14:22
 */
@Slf4j
class ChatServer extends AbstractServer {

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();

        chatServer.getServer().addEventListener("chat.event", ChatMessage.class, (client, data, ackRequest) -> {
            log.info("消息进来：{} => {}", client.getSessionId(), data.toString());
            // broadcast messages to all clients
            chatServer.getServer().getBroadcastOperations().sendEvent("chat.event", new ChatMessage("xxoo", RandomStringUtils.randomAlphabetic(10)));
        });

        chatServer.start();
    }

}