package com.ljt.study.aop.bean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.ljt.study.aop.meta.Auditable;

import java.util.Arrays;

/**
 * @author LiJingTang
 * @version 2015年9月14日下午3:43:49
 */
class AdviceParamBean {

	/**
	 * 任何通知方法可以将第一个参数定义为org.aspectj.lang.JoinPoint类型
	 * (环绕通知需要定义第一个参数为ProceedingJoinPoint类型， 它是 JoinPoint 的一个子类)。
	 */
	@Before("com.ljt.study.aop.bean.PointcutBean.businessService()")
	public void accessJoinPoint(JoinPoint joinPoint) {
		// 返回方法参数
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		// 返回代理对象
		System.out.println(joinPoint.getThis());
		// 返回目标
		System.out.println(joinPoint.getTarget());
		// 返回正在被通知的方法相关信息
		System.out.println(joinPoint.getSignature());
		// 打印出正在被通知的方法的有用信息
		System.out.println(joinPoint.toString());
	}

	/**
	 * 切入点表达式的 args(argMsg,..) 部分有两个目的：首先它保证了 只会匹配那些接受至少一个参数的方法的执行，
	 * 而且传入的参数必须是String类型的实例， 其次它使得在通知体内可以通过argMsg参数访问实际的String对象。
	 */
	@Before("com.ljt.study.aop.bean.PointcutBean.businessService() && args(argMsg,..)")
	public void passingParameters(String argMsg) {
		System.out.println(argMsg);
	}

	@Pointcut("com.ljt.study.aop.bean.PointcutBean.businessService() && args(argMsg,..)")
	private void pointcut(String argMsg) {
	}
	@Before("pointcut(argMsg)")
	public void validateArgs(String argMsg) {
		System.out.println(argMsg);
	}

	@Before("com.ljt.study.aop.bean.PointcutBean.businessService() && @annotation(auditable)")
	public void audit(Auditable auditable) {
		System.out.println(auditable.value());
	}
	
	/**
	 * 绑定在通知上的参数依赖切入点表达式的匹配名，并借此在（通知和切入点）的方法签名中声明参数名。
	 * 参数名无法 通过Java反射来获取，所以Spring AOP使用如下的策略来确定参数名字： 
	 * 如果参数名字已经被用户明确指定，则使用指定的参数名： 通知和切入点注解有一个额外的"argNames"属性，
	 * 该属性用来指定所注解的方法的参数名 - 这些参数名在运行时是可以 访问的
	 */
	@Before(value="com.ljt.study.aop.bean.PointcutBean.anyPublicMethod() && target(bean) && @annotation(auditable)",
	        argNames="bean,auditable")
	public void audit(Object bean, Auditable auditable) {
		System.out.println(auditable.value());
	}
	
	/**
	 * ? 如果第一个参数是JoinPoint， ProceedingJoinPoint， 或者JoinPoint.StaticPart类型， 
	 * 你可以在argNames属性的值中省去参数的名字。例如，如果你修改前面的通知来获取连接点对象， "argNames"属性就不必包含它
	 */
	@Before(value="com.ljt.study.aop.bean.PointcutBean.anyPublicMethod() && target(bean) && @annotation(auditable)",
	        argNames="bean,auditable")
	public void audit(JoinPoint joinPoint, Object bean, Auditable auditable) {
		System.out.println(auditable.value());
	}
	
	/**
	 * ? 对于第一个JoinPoint， ProceedingJoinPoint，和 JoinPoint.StaticPart类型的参数特殊处理特别适合 没有集合其它连接上下文的通知。在这种情部下，你可以简单的省略“argNames”属性。
	 */
	@Before("com.ljt.study.aop.bean.PointcutBean.anyPublicMethod()")
	public void audit(JoinPoint jp) {
	}
	
	/**
	 * ? 编写一个带参数的的proceed()调用， 使得在Spring AOP和AspectJ中都能正常工作。解决方法是仅仅确保通知签名按顺序绑定方法参数
	 */
	@Around("execution(List<Account> find*(..)) && com.ljt.study.aop.bean.PointcutBean.inDataAccessLayer() && args(accountHolderNamePattern)")
	public Object preProcessQueryPattern(ProceedingJoinPoint pjp, String accountHolderNamePattern) throws Throwable {
	    String newPattern = preProcess(accountHolderNamePattern);
	    
	    return pjp.proceed(new Object[] {newPattern});
	}
	private String preProcess(String accountHolderNamePattern) {
		return null;
	}
	
}
