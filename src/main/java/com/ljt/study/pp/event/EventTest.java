package com.ljt.study.pp.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LiJingTang
 * @date 2020-01-21 08:36
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EventConfig.class)
public class EventTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void publish() {
        applicationContext.publishEvent(new HelloWordEvent("触发事件"));
        applicationContext.publishEvent(new HelloWordEvent("再次触发事件"));
    }

}
