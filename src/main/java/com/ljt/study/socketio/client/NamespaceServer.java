package com.ljt.study.socketio.client;

import com.corundumstudio.socketio.SocketIONamespace;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2022-04-01 17:03
 */
@Slf4j
class NamespaceServer extends AbstractServer {

    public static void main(String[] args) {
        NamespaceServer nsServer = new NamespaceServer();

        final SocketIONamespace ns1 = nsServer.getServer().addNamespace("/ns1");
        ns1.addEventListener("ns.event", ChatMessage.class, (client, data, ackRequest) -> {
            log.info("ns1: {}", data.toString());
            ns1.getBroadcastOperations().sendEvent("message", data);
        });

        final SocketIONamespace ns2 = nsServer.getServer().addNamespace("/ns2");
        ns2.addEventListener("ns.event", ChatMessage.class, (client, data, ackRequest) -> {
            log.info("ns2: {}", data.toString());
            ns2.getBroadcastOperations().sendEvent("message", data);
        });

        nsServer.start();
    }

}
