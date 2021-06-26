package com.ljt.study.code.proxy.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LiJingTang
 * @date 2021-06-25 17:55
 */
@Slf4j
@Service
class CglibService {

    public void methodA() {
        log.info("A");
    }

    public void methodB() {
        log.info(this.getClass().getName());
        log.info("B");
    }

}
