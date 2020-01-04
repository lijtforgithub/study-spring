package com.ljt.study.pp.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author LiJingTang
 * @date 2020-01-04 09:57
 */
public class Car implements InitializingBean, DisposableBean {

    public Car() {
        super();
        System.out.println("...constructor...");
    }

    public void initMethod() {
        System.out.println("...initMethod...");
    }

    public void destroyMethod() {
        System.out.println("...destroyMethod...");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("...afterPropertiesSet...");
    }

    @Override
    public void destroy() {
        System.out.println("...destroy...");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("...postConstruct...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("...preDestroy...");
    }

}
