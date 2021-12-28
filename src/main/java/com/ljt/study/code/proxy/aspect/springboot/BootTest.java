package com.ljt.study.code.proxy.aspect.springboot;

import com.ljt.study.code.proxy.aspect.bean.CglibService;
import com.ljt.study.code.proxy.aspect.bean.JdkService;
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
        jdkService.methodB();
        log.info("jdkService = {}", jdkService.getClass().getName());
    }

    @Test
    void testCglib() {
        cglibService.methodB();
        log.info("cglibService = {}", cglibService.getClass().getName());
    }

}
