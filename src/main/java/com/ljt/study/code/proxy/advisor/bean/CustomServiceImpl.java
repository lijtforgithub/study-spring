package com.ljt.study.code.proxy.advisor.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author LiJingTang
 * @date 2021-06-26 11:41
 */
@Slf4j
@Component
class CustomServiceImpl implements CustomService {

    @Override
    public void methodA() {
        log.info("A");
    }

    @Override
    @Log
    public void methodB() {
        System.out.println(this.getClass());
        methodA();
        log.info("B");
    }

}
