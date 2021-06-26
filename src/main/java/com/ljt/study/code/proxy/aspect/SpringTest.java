package com.ljt.study.code.proxy.aspect;

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
        log.info(jdkService.getClass().getName());
        jdkService.methodB();
    }

    @Test
    void testCglib() {
        log.info(cglibService.getClass().getName());
        cglibService.methodB();
    }

}
