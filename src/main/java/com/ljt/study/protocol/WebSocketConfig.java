package com.ljt.study.protocol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * @author LiJingTang
 * @date 2022-03-10 16:52
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(wsTextHandler(), "/wsHandler")
//                .setAllowedOrigins("*")
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