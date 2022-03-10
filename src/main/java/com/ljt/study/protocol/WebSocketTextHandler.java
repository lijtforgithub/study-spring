package com.ljt.study.protocol;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author LiJingTang
 * @date  2022-03-10 16:28
 */
@Slf4j
class WebSocketTextHandler extends TextWebSocketHandler {

    @SneakyThrows
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        super.afterConnectionEstablished(session);
        String id = session.getId();
        log.info("客户端：{}", id);

        session.sendMessage(new TextMessage("客户端编号 " + id));
    }

    @SneakyThrows
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String id = session.getId();
        String msg = message.getPayload();
        log.info("客户端{}发送数据：{}", id, msg);

        session.sendMessage(new TextMessage("服务端响应数据：" + msg));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        String id = session.getId();
        log.info("客户端" + id + "关闭成功");
    }
}