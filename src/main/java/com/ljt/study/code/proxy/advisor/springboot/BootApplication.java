package com.ljt.study.code.proxy.advisor.springboot;

import com.ljt.study.code.proxy.advisor.spring.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author LiJingTang
 * @date 2021-06-26 15:28
 */
@Import(Config.class)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
