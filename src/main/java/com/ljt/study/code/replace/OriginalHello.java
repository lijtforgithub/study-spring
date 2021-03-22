package com.ljt.study.code.replace;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2021-03-18 20:31
 */
@Slf4j
public class OriginalHello {

    public void sayHello() {
        log.info("Hello, World");
    }

    public void sayHello(String name) {
        log.info("Hello, {}", name);
    }

}
