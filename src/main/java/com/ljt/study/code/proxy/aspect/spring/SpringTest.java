package com.ljt.study.code.proxy.aspect.spring;

import com.ljt.study.code.proxy.aspect.bean.CglibService;
import com.ljt.study.code.proxy.aspect.bean.JdkService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author LiJingTang
 * @date 2021-06-25 17:56
 */
@Slf4j
@SpringJUnitConfig(Config.class)
class SpringTest {

    @Autowired
    private JdkService jdkService;
    @Autowired
    private CglibService cglibService;

    @Test
    void testJdk() {
        jdkService.methodB();
        log.info("jdkService = {}", jdkService.getClass().getName());
    }

    @Test
    void testCglib() {
        cglibService.methodB();
        log.info("cglibService = {}", cglibService.getClass().getName());
    }

}
