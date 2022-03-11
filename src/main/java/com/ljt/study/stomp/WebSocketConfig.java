package com.ljt.study.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author LiJingTang
 * @date 2022-03-11 9:28
 */
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // .withSockJS()
        registry.addEndpoint("/stomp");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 所有 /app 开头的都会路由到@MessageMapping或@SubscribeMapping注解方法中
        registry.setApplicationDestinationPrefixes("/app");
        // @SendTo 给客户端回复的消息都要以此开头
        registry.enableSimpleBroker("/topic", "/xx");
        // 中继代理 基于RabbitMQ /mq 开头的都会推送的消息中间件
        registry.enableStompBrokerRelay("/mq");
    }

}
