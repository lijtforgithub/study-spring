package com.ljt.study.code.aware;

import org.springframework.beans.factory.Aware;

/**
 * @author LiJingTang
 * @date 2021-03-14 11:44
 */
public interface PrintAware extends Aware {

    /**
     * 打印一句话
     */
    default void printClass() {
        System.out.println("自定义Aware接口：" + this.getClass());
    }

}
