package com.ljt.study.code.mvc.initstrategies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author LiJingTang
 * @date 2021-08-11 10:27
 */
@Slf4j
@RestController
@SpringBootApplication
class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }


    @Autowired
    private DispatcherServlet dispatcherServlet;

//    @ConditionalOnBean(DispatcherServlet.class)
//    @EventListener
//    void contextRefreshListener(ContextRefreshedEvent event) {
//        log.info("打开 contextRefreshListener");
//        dispatcherServlet.onApplicationEvent(event);
//        log.info("上传组件：{}", dispatcherServlet.getMultipartResolver());
//    }

    @GetMapping
    public String index() {
        return "init Strategies";
    }

}
