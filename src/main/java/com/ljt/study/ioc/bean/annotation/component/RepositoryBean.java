package com.ljt.study.ioc.bean.annotation.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:07
 */
@Scope("prototype") // 容器初始化的就不会实例化了
@Repository
public class RepositoryBean {

    public RepositoryBean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

}
