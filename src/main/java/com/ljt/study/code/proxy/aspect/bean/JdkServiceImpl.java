package com.ljt.study.code.proxy.aspect.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author LiJingTang
 * @date 2021-06-25 17:53
 */
@Slf4j
@Service
class JdkServiceImpl implements JdkService {

    @Override
    public void methodA() {
        log.info("A");
    }

    @Override
    public void methodB() {
        log.info(this.getClass().getName());
        log.info("B");
    }

}
