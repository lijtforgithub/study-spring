package com.ljt.study.pp.lookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 单例bean组合原型bean
 * ApplicationContext 第一种办法
 *
 * @author LiJingTang
 * @date 2020-01-04 09:31
 */
@Slf4j
@Component
public class SingletonContextGetBean {

    /**
     * 第一种解决办法
     */
    @Autowired
    private ApplicationContext applicationContext;

    public void print() {
        PrototypeBean bean = applicationContext.getBean(PrototypeBean.class);
        log.info("getBean | PrototypeBean hashCode = {}", bean.hashCode());
        bean.say();
    }

}
