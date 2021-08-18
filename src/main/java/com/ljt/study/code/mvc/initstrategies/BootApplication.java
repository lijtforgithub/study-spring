package com.ljt.study.code.mvc.initstrategies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author LiJingTang
 * @date 2021-08-11 10:27
 */
@Slf4j
@SpringBootApplication
class BootApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }


    @Autowired
    private DispatcherServlet dispatcherServlet;

    /**
     * org.springframework.web.servlet.FrameworkServlet#configureAndRefreshWebApplicationContext(org.springframework.web.context.ConfigurableWebApplicationContext)
     * wac.addApplicationListener(new SourceFilteringListener(wac, new ContextRefreshListener()));
     */

//    @ConditionalOnBean(DispatcherServlet.class)
//    @EventListener
//    void contextRefreshListener(ContextRefreshedEvent event) {
//        log.info("打开 contextRefreshListener");
//        dispatcherServlet.onApplicationEvent(event);
//        log.info("上传组件：{}", dispatcherServlet.getMultipartResolver());
//    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("SpringMVC 九大组件");
        log.info("MultipartResolver: {}", dispatcherServlet.getMultipartResolver());
        log.info("HandlerMapping: {}", dispatcherServlet.getHandlerMappings());
    }

}
