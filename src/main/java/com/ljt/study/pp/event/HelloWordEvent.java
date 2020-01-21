package com.ljt.study.pp.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @author LiJingTang
 * @date 2020-01-21 08:32
 */
@Slf4j
public class HelloWordEvent extends ApplicationEvent {

    public HelloWordEvent(Object source) {
        super(source);
        log.info("{} : {}", HelloWordEvent.class.getSimpleName(), source);
    }

}
