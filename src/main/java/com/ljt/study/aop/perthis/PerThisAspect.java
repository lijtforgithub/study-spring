package com.ljt.study.aop.perthis;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * 默认情况下，在application context中每一个切面都会有一个实例。AspectJ把这个叫做单例化模型。 
 * 也可以用其他的生命周期来定义切面：Spring支持AspectJ的 perthis 和pertarget实例化模型（现在还不支持percflow、percflowbelow 和pertypewithin）。 
 * 
 * 这个'perthis'子句的效果是每个独立的service对象执行一个业务时都会 创建一个切面实例）。
 * 在service对象上第一次调用方法的时候，切面实例将被创建。切面在service对象失效的同时失效。 
 * 在切面实例被创建前，所有的通知都不会被执行，一旦切面对象创建完成， 定义的通知将会在匹配的连接点上执行，但是只有当service对象是和切面关联的才可以。 
 * 
 * pertarget实例模型的跟perthis完全一样，只不过是为每个匹配于连接点 的独立目标对象创建一个切面实例。
 * 
 * @author LiJingTang
 * @version 2015年9月17日下午2:29:23
 */
@Aspect("perthis(execution(* com.ljt.study.aop.service.*.*(..)))")
class PerThisAspect {

    PerThisAspect() {
        System.out.println(this.getClass() + "实例化");
    }

    @After("execution(* com.ljt.study.aop.service.*.*(..))")
    void after() {
        System.out.println("	" + this.getClass().getName() + "...after()...");
    }

}
