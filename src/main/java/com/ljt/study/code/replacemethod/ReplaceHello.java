package com.ljt.study.code.replacemethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author LiJingTang
 * @date 2021-03-18 20:33
 */
@Slf4j
public class ReplaceHello implements MethodReplacer {

    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        log.info("replace 调用");
        Arrays.stream(args).forEach(str -> log.info("Hello, 【{}】", str));
        return obj;
    }

}
