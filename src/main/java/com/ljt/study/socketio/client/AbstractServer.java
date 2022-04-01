package com.ljt.study.socketio.client;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ExceptionListener;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJingTang
 * @date 2022-04-01 15:19
 */
@Getter
@Slf4j
class AbstractServer {

    private final SocketIOServer server;

    AbstractServer() {
        this.server = new SocketIOServer(getConfig());
        this.server.addConnectListener(client -> log.info("有客户端连接：{}", client.getSessionId()));
        this.server.addDisconnectListener(client -> log.info("有客户端断开连接：{}", client.getSessionId()));

        this.server.addEventInterceptor((client, eventName, args, ackRequest) -> log.info("客户端：{} 事件：{} 参数：{}", client.getSessionId(), eventName, args));
    }

    protected Configuration getConfig() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);

        Configuration config = new Configuration();
//        config.setHostname("localhost");
        config.setPort(9090);
        config.setSocketConfig(socketConfig);

        config.setExceptionListener(new ExceptionListener() {
            @Override
            public void onEventException(Exception e, List<Object> args, SocketIOClient client) {

            }

            @Override
            public void onDisconnectException(Exception e, SocketIOClient client) {

            }

            @Override
            public void onConnectException(Exception e, SocketIOClient client) {

            }

            @Override
            public void onPingException(Exception e, SocketIOClient client) {

            }

            @Override
            public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
                return false;
            }
        });

        return config;
    }

    @SneakyThrows
    protected void start() {
        server.start();
        log.info("---------- server start ----------");

        TimeUnit.MINUTES.sleep(5);
        server.stop();
        log.info("---------- server stop ----------");
    }

}
