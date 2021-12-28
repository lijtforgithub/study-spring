package com.ljt.study.code.proxy.advisor.springboot;

import com.ljt.study.code.proxy.advisor.bean.CustomService;
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
    private CustomService customService;

    @Test
    void test() {
        customService.methodB();
        log.info("customService = {}", customService.getClass().getName());
    }

}
