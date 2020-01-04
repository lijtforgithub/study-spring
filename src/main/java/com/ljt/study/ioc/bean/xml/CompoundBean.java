package com.ljt.study.ioc.bean.xml;

/**
 * 复合
 *
 * @author LiJingTang
 * @date 2020-01-04 10:44
 */
public class CompoundBean {

    private Bean bean = new Bean();

    public CompoundBean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

    public Bean getBean() {
        return bean;
    }

}
