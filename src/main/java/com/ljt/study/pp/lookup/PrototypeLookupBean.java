package com.ljt.study.pp.lookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 原型bean组合原型bean
 *
 * @author LiJingTang
 * @date 2020-01-04 09:43
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class PrototypeLookupBean {

    public void print() {
        PrototypeBean bean = getPrototypeBean();
        log.info("@Lookup | PrototypeBean hashCode = {}", bean.hashCode());
        bean.say();
    }

    @Lookup
    protected abstract PrototypeBean getPrototypeBean();

}
