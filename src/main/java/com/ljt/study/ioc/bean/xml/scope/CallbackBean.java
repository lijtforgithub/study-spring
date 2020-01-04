package com.ljt.study.ioc.bean.xml.scope;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:50
 */
public class CallbackBean implements InitializingBean, DisposableBean {

    public CallbackBean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("	" + this.getClass().getName() + "实现了接口" + this.getClass().getInterfaces()[0].getName()
                + "的afterPropertiesSet()方法来实现初始化回调");
    }

    @Override
    public void destroy() {
        System.out.println("	" + this.getClass().getName() + "实现了接口" + this.getClass().getInterfaces()[1].getName()
                + "的destroy()方法来实现销毁回调");
    }

    public void init() {
        System.out.println("	" + this.getClass().getName() + "调用init()方法");
    }

    public void personalDestroy() {
        System.out.println("	" + this.getClass().getName() + "调用personalDestroy()方法");
    }

}
