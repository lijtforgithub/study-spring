package com.ljt.study.ioc.di.xml;

import com.ljt.study.ioc.bean.xml.Bean;

import java.beans.ConstructorProperties;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:31
 */
public class ConstructorBean {

    private int value;
    private String explain;
    private Bean bean;

    public ConstructorBean(Bean bean, String explain) {
        this.bean = bean;
        this.explain = explain;
        System.out.println(this.getClass().getName() + "基于构造方法的依赖注入" + this.bean.getClass().getName() + this.explain);
    }

    public ConstructorBean(String explain, int value) {
        this.explain = explain;
        this.value = value;
        System.out.println(this.getClass().getName() + "基于构造方法的依赖注入 explain: " + this.explain + "value: " + this.value);
    }

    @ConstructorProperties({"aliasAttrName"})
    public ConstructorBean(String explain) {
        System.out.println(this.getClass().getName() + "基于构造方法的依赖注入 explain: " + this.explain);
    }

}
