package com.ljt.study.aop.bean;

import com.ljt.study.aop.meta.Auditable;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/**
 * 切面(用@Aspect注解的类)和其他类一样有方法和字段定义。他们也可能包括切入点，通知和引入(inter-type)声明
 * 拥有切面的类本身不可能是其它切面中通知的目标。一个类上面的@Aspect注解标识它为一个切面，并且从自动代理中排除它
 * 
 * @author LiJingTang
 * @version 2015年8月30日 下午7:58:49
 */
@Aspect
@Order(50)
public class CustomAspect {

	public CustomAspect() {
		System.out.println(this.getClass() + "实例化");
	}

	@Pointcut("execution(* save*(..))")
	private void saveMethod() {}

	@Pointcut("within(com.ljt.study.aop.service..*)")
	private void service() {}

	@Pointcut("service() && saveMethod()")
	private void serviceSaveMethod() {}
	
	@Before("serviceSaveMethod()")
	public void before(JoinPoint joinPoint) {
		System.out.println("	" + this.getClass().getName() + "...before()...");
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		System.out.println(joinPoint.getThis() + " - " + joinPoint.getThis().getClass());
		System.out.println(joinPoint.getTarget() + " - " + joinPoint.getTarget().getClass());
		System.out.println(joinPoint.getThis() == joinPoint.getTarget());
		System.out.println(joinPoint.getSignature());
		System.out.println(joinPoint.toString());
		System.out.println(joinPoint.getSourceLocation());
		System.out.println(joinPoint.getKind());
		System.out.println(joinPoint.getStaticPart());
	}

	@After("execution(* com.ljt.study.aop.service.*.save*(..))")
	public void after() {
		System.out.println("	" + this.getClass().getName() + "...after()...");
	}
	
//	@Pointcut("this(com.ljt.study.aop.service.ParameterService)")
//	@Pointcut("target(com.ljt.study.aop.service.ParameterService)")
	@Pointcut("execution(* com.ljt.study.aop.service.ParameterService.deleteParam(..))")
	private void param() {}
	
	@AfterReturning(pointcut="param()", returning="retVal")
	public void afterReturning(Object retVal) {
		System.out.println("	" + this.getClass().getName() + "...afterReturning()..." + retVal);
	}
	
	@AfterThrowing(pointcut="param()", throwing="e")
	public void afterThrowing(RuntimeException e) {
		System.out.println("	" + this.getClass().getName() + "...afterThrowing()..." + e.getMessage());
	}
	
	@Before("param() && args(id, msg,..)")
	public void passingParameters(Long id, String msg) {
		System.out.println("	" + this.getClass().getName() + "...passingParameters()..." + id + msg);
	}
	
	@Before("bean(parameterService) && @annotation(auditable)")
	public void audit(Auditable auditable) {
		System.out.println("	" + this.getClass().getName() + "...audit()..." + auditable.value());
	}
	
	@Around("param() && args(id, msg,..)")
	public Object around(ProceedingJoinPoint pjp, Long id, String msg) throws Throwable {
	    if (null == id || id == 0L) {
	    	System.out.println("	" + this.getClass().getName() + "...around()...ID不合法");
	    }
	    
	    Object[] params = new Object[] {id, msg};
	    Object obj = pjp.proceed(params);
	    
	    System.out.println("	" + msg + obj);
	    return obj;
	}
	
	@Pointcut("service() && execution(* orderMethod())")
	private void order(){}
	
	@Before("order()")
	public void before_order(JoinPoint joinPoint) {
		System.out.println("	" + this.getClass().getName() + "...before_order()...");
	}

	@After("order()")
	public void after_order() {
		System.out.println("	" + this.getClass().getName() + "...before_order()...");
	}
	
}
