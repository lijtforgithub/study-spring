package com.ljt.study.code.mvc.mapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author LiJingTang
 * @date 2021-12-09 16:58
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class RegisterMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterMappingApplication.class);
    }

}
