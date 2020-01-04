package com.ljt.study.ioc.bean.xml;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:47
 */
public class LazyBean {

    private String explain;

    public LazyBean() {
    }

    public LazyBean(String explain) {
        this.explain = explain;
        System.out.println(this.getClass().getName() + this.explain);
    }

}
