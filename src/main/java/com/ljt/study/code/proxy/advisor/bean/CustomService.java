package com.ljt.study.code.proxy.advisor.bean;

/**
 * @author LiJingTang
 * @date 2021-06-26 15:26
 */
public interface CustomService {

    @Log
    void methodA();

    void methodB();

}
