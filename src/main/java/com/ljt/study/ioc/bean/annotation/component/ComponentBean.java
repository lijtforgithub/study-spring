package com.ljt.study.ioc.bean.annotation.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:06
 */
@Getter
@Setter
@Component // 一般情况下，Spring管理的组件，自动检测组件的默认和最多使用的范围是单例范
public class ComponentBean {

    private int i;
    private String explain;

    public ComponentBean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

    public ComponentBean(String explain) {
        this.explain = explain;
    }

    public ComponentBean(String explain, int i) {
        this.i = i;
        this.explain = explain;
    }

}
