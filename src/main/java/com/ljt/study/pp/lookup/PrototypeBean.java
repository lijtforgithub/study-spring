package com.ljt.study.pp.lookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 原型bean
 *
 * @author LiJingTang
 * @date 2020-01-04 09:32
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBean {

    public void say() {
        log.info("chen pei pei");
    }

}
