package com.ljt.study.sse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author LiJingTang
 * @date 2021-04-27 17:53
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SseApplication.class);
    }

}
