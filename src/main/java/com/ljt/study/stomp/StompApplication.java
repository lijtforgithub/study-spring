package com.ljt.study.stomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * rabbitmq-plugins enable rabbitmq_stomp
 * rabbitmq-plugins enable rabbitmq_web_stomp
 *
 * @author LiJingTang
 * @date 2022-03-11 9:29
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class StompApplication {

    public static void main(String[] args) {
        SpringApplication.run(StompApplication.class);
    }

}
