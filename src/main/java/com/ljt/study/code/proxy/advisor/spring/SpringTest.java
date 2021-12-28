package com.ljt.study.code.proxy.advisor.spring;

import com.ljt.study.code.proxy.advisor.bean.CustomService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author LiJingTang
 * @date 2021-06-26 11:43
 */
@Slf4j
@SpringJUnitConfig(Config.class)
class SpringTest {

    @Autowired
    private CustomService customService;

    @Test
    void test() {
        customService.methodB();
        log.info("customService = {}", customService.getClass().getName());
    }

}
