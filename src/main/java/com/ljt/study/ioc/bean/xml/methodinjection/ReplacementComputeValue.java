package com.ljt.study.ioc.bean.xml.methodinjection;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class ReplacementComputeValue implements MethodReplacer {

    public Object reimplement(Object o, Method m, Object[] args) throws Throwable {
        String input = (String) args[0]; // 获取输入值，并处理它，返回一个计算的结果
        
        return input;
    }
}