package com.ljt.study.ioc.bean.xml.scope;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:51
 */
public class SingletonBean {

    public SingletonBean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

    public void init() {
        System.out.println("	" + this.getClass().getName() + "调用init()方法");
    }

    public void destroy() {
        System.out.println("	" + this.getClass().getName() + "调用destroy()方法");
    }

}
