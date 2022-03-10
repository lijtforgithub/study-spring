package com.ljt.study.protocol;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static javax.websocket.CloseReason.CloseCodes.NORMAL_CLOSURE;

/**
 * @author LiJingTang
 * @date 2022-03-10 10:35
 */
@Slf4j
@Component
@ServerEndpoint("/ws")
public class WsController {

    @OnOpen
    @SneakyThrows
    public void open(Session session) {
        String id = session.getId();
        log.info("客户端：{}", id);
        session.getBasicRemote().sendText("客户端编号 " + id);

        Executors.newFixedThreadPool(1).submit(new WsTask(session));
    }

    @OnClose
    public void close(Session session) {
        String id = session.getId();
        log.info("客户端" + id + "关闭成功");
    }

    @SneakyThrows
    @OnMessage
    public void message(Session session, String msg) {
        String id = session.getId();
        log.info("客户端{}发送数据：{}", id, msg);
        session.getBasicRemote().sendText("服务端响应数据：" + msg);
    }

}

class WsTask implements Runnable {

    private final Session session;

    WsTask(Session session) {
        this.session = session;
    }

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            TimeUnit.SECONDS.sleep(10);
            session.getBasicRemote().sendText(LocalDateTime.now().toString());
        }

        session.close(new CloseReason(NORMAL_CLOSURE, "服务端发起关闭"));
    }

}
