package com.ljt.study.ioc.bean.xml;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:42
 */
@Getter
@Setter
public class Bean {

    private String emptyString;
    private String nullString;

    public Bean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

    private static class Nested {

        public Nested() {
            System.out.println(this.getClass().getName() + "实例化一个私有的静态内部类");
        }
    }

    private class Inner {

        public Inner() {
            System.out.println(this.getClass().getName() + "实例化一个私有的成员内部类");
        }
    }

}
