package com.ljt.study.code.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author LiJingTang
 * @date 2023-04-13 15:31
 */
@Slf4j
@Data
@Component
public class ComponentBean {

    @Value("${component.bean.id:0}")
    private Integer id;
    @Value("${component.bean.name:xxoo}")
    private String name;


    @PreDestroy
    void stop() {
        log.info("一般用来关闭线程池之类");
    }

}
