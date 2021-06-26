package com.ljt.study.code.proxy.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author LiJingTang
 * @date 2021-06-25 17:59
 */
@Slf4j
@Aspect
@Component
class MethodAspect {

    @Pointcut("execution(* com.ljt.study.code.proxy.aspect.*.method*())")
    public void method() {}

    @SneakyThrows
    @Around("method()")
    public void around(ProceedingJoinPoint joinPoint) {
        log.info("代理逻辑：{}", joinPoint.getSignature());
        joinPoint.proceed();
    }

}
