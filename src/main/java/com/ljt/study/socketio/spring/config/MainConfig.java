package com.ljt.study.socketio.spring.config;

import com.corundumstudio.socketio.AckMode;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.ljt.study.socketio.spring.prop.SocketProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiJingTang
 * @date 2022-04-02 14:01
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SocketProperties.class)
class MainConfig {

    @Bean
    SocketIOServer server(SocketProperties properties) {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setAckMode(AckMode.MANUAL);
        config.setSocketConfig(socketConfig);
        config.setPort(properties.getPort());
        config.setBossThreads(properties.getBossThreads());
        config.setWorkerThreads(properties.getWorkerThreads());
        config.setAllowCustomRequests(properties.getIsAllowCustomRequests());
        config.setUpgradeTimeout(properties.getUpgradeTimeout());
        config.setPingTimeout(properties.getPingTimeout());
        config.setPingInterval(properties.getPingInterval());
        return new SocketIOServer(config);
    }

    @Bean
    SpringAnnotationScanner springAnnotationScanner(SocketIOServer server) {
        return new SpringAnnotationScanner(server);
    }

}
