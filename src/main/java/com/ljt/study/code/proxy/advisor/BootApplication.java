package com.ljt.study.code.proxy.advisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author LiJingTang
 * @date 2021-06-26 15:28
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
