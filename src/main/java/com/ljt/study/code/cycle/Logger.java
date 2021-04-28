package com.ljt.study.code.cycle;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2021-03-22 17:41
 */
@Slf4j
public class Logger {

    public void recordBefore() {
        log.info("before");
    }

    public void recordAfter() {
        log.info("after");
    }

}