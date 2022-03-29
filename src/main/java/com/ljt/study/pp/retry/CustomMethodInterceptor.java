package com.ljt.study.pp.retry;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author jtli3
 * @date 2022-03-29 20:31
 */
@Slf4j
class CustomMethodInterceptor implements MethodInterceptor {

    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation methodInvocation) throws Throwable {
        log.info("--invoke start--");
        Object obj = methodInvocation.proceed();
        log.info("--invoke end--");
        return obj;
    }

}
