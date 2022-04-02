package com.ljt.study.socketio.spring.controller;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.ljt.study.socketio.client.ChatMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author LiJingTang
 * @date 2022-04-02 14:12
 */
@Slf4j
@Controller
public class ChatController {

    @Autowired
    private SocketIOServer server;

    @OnEvent("chat.event")
    void onEvent(SocketIOClient client, ChatMessage data, AckRequest ackRequest) {
        log.info("消息进来：{} => {}", client.getSessionId(), data.toString());
    }

}
