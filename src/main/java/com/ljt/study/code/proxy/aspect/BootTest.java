package com.ljt.study.code.proxy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiJingTang
 * @date 2021-06-26 09:13
 */
@Slf4j
@SpringBootTest
class BootTest {

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
