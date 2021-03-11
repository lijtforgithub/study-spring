package com.ljt.study.pp.event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @author LiJingTang
 * @date 2020-01-21 08:36
 */
@SpringBootTest(classes = EventConfig.class)
public class EventTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void publish() {
        applicationContext.publishEvent(new HelloWordEvent("触发事件"));
        applicationContext.publishEvent(new HelloWordEvent("再次触发事件"));
    }

}
