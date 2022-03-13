package com.ljt.study.stomp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.*;

/**
 * @author LiJingTang
 * @date 2022-03-11 9:28
 */
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private TaskScheduler taskScheduler;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // .withSockJS()
        registry.addEndpoint("/stomp");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 所有 /app 开头的都会路由到@MessageMapping或@SubscribeMapping注解方法中
        registry.setApplicationDestinationPrefixes("/app");
        // 内置消息代理进行订阅和广播 @SendTo 给客户端回复的消息都要以此开头
        registry.enableSimpleBroker("/topic", "/xx");
//                .setHeartbeatValue(new long[] {10000, 20000})
//                .setTaskScheduler(taskScheduler);
        // 中继代理 基于RabbitMQ 开头的都会推送的消息中间件
        registry.enableStompBrokerRelay("/topic", "/queue");

        // 保证消息顺序性
        registry.setPreservePublishOrder(true);

    }

    /**
     * 添加拦截器
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        WebSocketMessageBrokerConfigurer.super.configureClientInboundChannel(registration);
    }

    @EventListener
    void brokerAvailabilityEvent(BrokerAvailabilityEvent event) {
        log.info("代理 {}", event.toString());
    }

    @EventListener
    void sessionConnectEvent(SessionConnectEvent event) {
        log.info("有连接进来 {}", event.toString());
    }

    @EventListener
    void sessionConnectedEvent(SessionConnectedEvent event) {
        log.info("已连接 {}", event.toString());
    }

    @EventListener
    void sessionSubscribeEvent(SessionSubscribeEvent event) {
        log.info("订阅 {}", event.toString());
    }

    @EventListener
    void sessionUnsubscribeEvent(SessionUnsubscribeEvent event) {
        log.info("取消订阅 {}", event.toString());
    }

    @EventListener
    void sessionDisconnectEvent(SessionDisconnectEvent event) {
        log.info("连接断开 {}", event.toString());
    }

}
