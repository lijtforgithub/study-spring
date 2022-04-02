package com.ljt.study.socketio.spring.config;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author LiJingTang
 * @date 2022-04-02 16:46
 */
@Slf4j
@Component
class SessionManage {

    @Autowired
    private SocketIOServer server;
    // 分布式的话 要放到redis
    private static final Map<String, Map<String, ConcurrentLinkedQueue<SocketIOClient>>> CLIENT_MAP = new ConcurrentHashMap<>();

    @OnConnect
    void onConnect(SocketIOClient client) {
        log.info("有客户端连接：{}", client.getSessionId());
        printHeaders(client);
        String orgId = client.getHandshakeData().getSingleUrlParam("orgId");
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        if (StringUtils.isAnyBlank(orgId, userId)) {
            log.warn("onConnect 信息不合法 orgId={} userId={}", orgId, userId);
            return;
        }

        Map<String, ConcurrentLinkedQueue<SocketIOClient>> userMap = CLIENT_MAP.get(orgId);
        if (Objects.isNull(userMap)) {
            userMap = new ConcurrentHashMap<>();
            CLIENT_MAP.put(orgId, userMap);
        }

        ConcurrentLinkedQueue<SocketIOClient> userList = userMap.get(userId);
        if (Objects.isNull(userList)) {
            userList = new ConcurrentLinkedQueue<>();
            userMap.put(userId, userList);
        }

        userList.add(client);

        log.info(CLIENT_MAP.toString());
    }

    @OnDisconnect
    void onDisconnect(SocketIOClient client) {
        log.info("有客户端断开连接：{}", client.getSessionId());

        String orgId = client.getHandshakeData().getSingleUrlParam("orgId");
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        if (StringUtils.isAnyBlank(orgId, userId)) {
            log.warn("onDisconnect 信息不合法 orgId={} userId={}", orgId, userId);
            return;
        }

        Map<String, ConcurrentLinkedQueue<SocketIOClient>> userMap = CLIENT_MAP.get(orgId);
        if (Objects.nonNull(userMap)) {
            ConcurrentLinkedQueue<SocketIOClient> userList = userMap.get(userId);
            if (!CollectionUtils.isEmpty(userList)) {
                userList.remove(client);
                log.info("移除：{}", client.getSessionId());

                if (CollectionUtils.isEmpty(userList)) {
                    userMap.remove(userId);
                    if (CollectionUtils.isEmpty(userMap)) {
                        CLIENT_MAP.remove(orgId);
                    }
                }
            }
        }

        log.info(CLIENT_MAP.toString());
    }


    private static void printHeaders(SocketIOClient client) {
        client.getHandshakeData().getHttpHeaders().entries().forEach(entry -> log.info("{} = {}", entry.getKey(), entry.getValue()));
    }

}
