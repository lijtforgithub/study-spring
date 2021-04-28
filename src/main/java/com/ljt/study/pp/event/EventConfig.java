package com.ljt.study.pp.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * @author LiJingTang
 * @date 2020-01-21 08:34
 */
@Slf4j
@Configuration
public class EventConfig {

    @EventListener
    public void start(HelloWordEvent event) {
        log.info("执行监听事件：{}", event.getSource());
    }

    /*@Bean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME)
    public ApplicationEventMulticaster multicaster() {
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        multicaster.setTaskExecutor(Executors.newSingleThreadExecutor());
        return multicaster;
    }*/

}
