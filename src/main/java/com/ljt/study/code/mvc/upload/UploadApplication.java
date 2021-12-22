package com.ljt.study.code.mvc.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author LiJingTang
 * @date 2021-12-20 15:36
 */

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class);
    }

}
