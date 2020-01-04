package com.ljt.study.ioc.bean.xml.methodinjection;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:01
 */
public class Command {

    private Object state;

    public Object execute() {
        return null;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

}
