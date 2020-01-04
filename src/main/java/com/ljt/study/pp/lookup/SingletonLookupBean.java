package com.ljt.study.pp.lookup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * 单例bean组合原型bean
 * @Lookup 第二种办法
 *
 * @author LiJingTang
 * @date 2020-01-04 09:36
 */
@Slf4j
@Component
public abstract class SingletonLookupBean {

    public void print() {
        PrototypeBean bean = getPrototypeBean();
        log.info("@Lookup | PrototypeBean hashCode = {}", bean.hashCode());
        bean.say();
    }

    @Lookup
    protected abstract PrototypeBean getPrototypeBean();

}
