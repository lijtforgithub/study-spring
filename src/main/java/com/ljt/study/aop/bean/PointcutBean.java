package com.ljt.study.aop.bean;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Spring AOP只支持Spring bean的方法执行连接点。所以你可以把切入点看做是Spring bean上方法执行的匹配
 * 在@AspectJ注解风格的AOP中，一个切入点签名通过一个普通的方法定义来提供，并且切入点表达式使用@Pointcut注解来表示（作为切入点签名的方法必须返回void 类型）。
 *
 * @author LiJingTang
 * @version 2015年9月14日下午2:20:41
 */
class PointcutBean {

    @Pointcut("within(com.ljt.study.aop.web..*)")
    public void inWebLayer() {
    }

    @Pointcut("within(com.ljt.study.aop.service..*)")
    public void inServiceLayer() {
    }

    @Pointcut("within(com.ljt.study.aop.dao..*)")
    public void inDataAccessLayer() {
    }

    @Pointcut("execution(* com.ljt.study.aop.service.*.*(..))")
    public void businessService() {
    }

}
