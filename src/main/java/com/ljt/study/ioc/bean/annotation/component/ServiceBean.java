package com.ljt.study.ioc.bean.annotation.component;

import org.springframework.stereotype.Service;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:07
 */
@Service
public class ServiceBean {

    public ServiceBean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

}
