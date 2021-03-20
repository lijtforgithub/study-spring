package com.ljt.study.code.bpp;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2021-03-19 10:11
 */
@Slf4j
public class Bpp {

    public Bpp() {
        log.info("Bpp 执行构造方法");
    }

    public void doSomething() {
        log.info("{} 调用doSomething", this.getClass().getSimpleName());
    }

}
