package com.ljt.study.protocol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author LiJingTang
 * @date 2021-04-27 17:53
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class ProtocolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProtocolApplication.class);
    }

    @Bean
    ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
