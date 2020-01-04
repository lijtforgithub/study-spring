package com.ljt.study.ioc.bean.xml.extend;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:54
 */
@Getter
@Setter
@ToString
public class Chinese {

    private String name;
    private String complexion;
    private int age;

    public Chinese() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

    public void init() {
        System.out.println("	XML配置继承了person的age重写了name");
    }

}
