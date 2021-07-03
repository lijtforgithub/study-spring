package com.ljt.study.aop.bean;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;

/**
 * @author LiJingTang
 * @version 2015年9月14日下午3:15:20
 */
class AdviceBean {

    @Before("com.ljt.study.aop.bean.PointcutBean.businessService()")
    public void before() {
    }

    /**
     * 返回后通知通常在一个匹配的方法返回的时候执行
     */
    @AfterReturning("com.ljt.study.aop.bean.PointcutBean.businessService()")
    public void afterReturning() {
    }

    /**
     * 在 returning属性中使用的名字必须对应于通知方法内的一个参数名。 当一个方法执行返回后，返回值作为相应的参数值传入通知方法。
     * 一个returning子句也限制了只能匹配到返回指定类型值的方法。 (在本例子中，返回值是Object类，也就是说返回任意类型都会匹配)
     */
    @AfterReturning(pointcut = "com.ljt.study.aop.bean.PointcutBean.businessService()", returning = "retVal")
    public void afterReturning(Object retVal) {
    }

    @AfterThrowing("com.ljt.study.aop.bean.PointcutBean.businessService()")
    public void afterThrowing() {
    }

    /**
     * 你通常会想要限制通知只在某种特殊的异常被抛出的时候匹配，你还希望可以在通知体内得到被抛出的异常。
     * 使用throwing属性不仅可以限制匹配的异常类型(如果你不想限制，请使用
     * Throwable作为异常类型)，还可以将抛出的异常绑定到通知的一个参数上。 在throwing属性中使用的名字必须与通知方法内的一个参数对应。
     * 当一个方法因抛出一个异常而中止后， 这个异常将会作为那个对应的参数送至通知方法。 throwing 子句也限制了只能匹配到抛出指定异常类型的方法
     * (示例为RuntimeException)。
     */
    @AfterThrowing(pointcut = "com.ljt.study.aop.bean.PointcutBean.businessService()", throwing = "e")
    public void afterThrowing(RuntimeException e) {
    }

    /**
     * 不论一个方法是如何结束的，最终通知都会运行。使用@After 注解来声明。最终通知必须准备处理正常返回和异常返回两种情况。通常用它来释放资源
     */
    @After("com.ljt.study.aop.bean.PointcutBean.businessService()")
    public void after() {
    }

    /**
     * 方法的调用者得到的返回值就是环绕通知返回的值。 例如：一个简单的缓存切面，如果缓存中有值，就返回该值，否则调用proceed()方法。
     * 请注意proceed可能在通知体内部被调用一次，许多次，或者根本不被调用，所有这些都是合法的。
     * <p>
     * 当传入一个Object[]对象的时候，处理的方法与通过AspectJ编译器处理环绕通知略有不同。
     * 对于使用传统AspectJ语言写的环绕通知来说，传入参数的数量必须和传递给环绕通知的参数数量匹配 （不是后台的连接点接受的参数数量），
     * 并且特定顺序的传入参数代替了将要绑定给连接点的原始值 （如果你看不懂不用担心）。
     * Spring采用的方法更加简单并且能更好匹配它基于代理（proxy-based）的执行语法， 如果你使用AspectJ的编译器和编织器来编译为Spring而写的@AspectJ切面和处理参数，你只需要知道这一区别即可。
     */
    @Around("com.ljt.study.aop.bean.PointcutBean.businessService()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = pjp.proceed();
        retVal = pjp.proceed(new Object[]{});

        return retVal;
    }

}
