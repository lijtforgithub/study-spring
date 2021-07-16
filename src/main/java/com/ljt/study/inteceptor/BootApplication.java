package com.ljt.study.inteceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  preHandle-1
 *  preHandle-2
 *  afterCompletion-1
 *
 * @author LiJingTang
 * @date 2021-07-16 18:06
 */
@Slf4j
@RestController
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

    @GetMapping("/")
    String index() {
        log.info("controller");
        return HttpStatus.OK.toString();
    }

}
