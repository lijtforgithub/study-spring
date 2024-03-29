package com.ljt.study.code.proxy.aspect.springboot;

import com.ljt.study.code.proxy.aspect.spring.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * springboot默认使用cglib代理
 * 配置文件设置 spring.aop.proxy-target-class: false 有接口的类会用jdk动态代理
 *
 * @author LiJingTang
 * @date 2021-06-26 09:14
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@Import(Config.class)
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
