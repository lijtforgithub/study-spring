package com.ljt.study.protocol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author LiJingTang
 * @date 2022-03-10 16:52
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsTextHandler(), "/wsHandler")
                .addInterceptors(webSocketInterceptor());
    }

    @Bean
    WebSocketTextHandler wsTextHandler() {
        return new WebSocketTextHandler();
    }

    @Bean
    WebSocketInterceptor webSocketInterceptor() {
        return new WebSocketInterceptor();
    }

}