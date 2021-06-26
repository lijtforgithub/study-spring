package com.ljt.study.code.proxy.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author LiJingTang
 * @date 2021-06-25 17:57
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.ljt.study.code.proxy")
class Config {
}
