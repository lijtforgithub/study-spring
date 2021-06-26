package com.ljt.study.code.proxy.advisor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2021-06-26 11:41
 */
@Slf4j
class CustomServiceImpl implements CustomService {

    @Override
    @Log
    public void methodA() {
        log.info("A");
    }

    @Override
    @Log
    public void methodB() {
        methodA();
        log.info("B");
    }

}
