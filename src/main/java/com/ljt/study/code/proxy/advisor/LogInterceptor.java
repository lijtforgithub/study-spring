package com.ljt.study.code.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author LiJingTang
 * @date 2021-06-26 14:21
 */
@Slf4j
class LogInterceptor implements MethodInterceptor {

    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation mi) throws Throwable {

        log.info("{} 方法开始", mi.getMethod().getName());
        Object proceed = mi.proceed();
        log.info("{} 方法结束", mi.getMethod().getName());

        return proceed;
    }

}
