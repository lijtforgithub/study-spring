OOP中模块化的关键单元是类（classes），AOP中模块化的单元则是切面。

 	通知: 许多AOP框架（包括Spring）都是以拦截器做通知模型，并维护一个以连接点为中心的拦截器链。
 	目标对象: 既然Spring AOP是通过运行时代理实现的，这个对象永远是一个被代理（proxied）对象。 
		在Spring中，AOP代理可以是JDK动态代理或者CGLIB代理。
	切入点表达式如何和连接点匹配是AOP的核心：Spring缺省使用AspectJ切入点语法。
	Spring和其他纯Java AOP框架一样，在运行时完成织入。 

	Spring目前仅支持使用方法调用作为连接点（join point）（在Spring bean上通知方法的执行）。
	虽然可以在不影响到Spring AOP核心API的情况下加入对成员变量拦截器支持，但Spring并没有实现成员变量拦截器。
	如果你需要把对成员变量的访问和更新也作为通知的连接点，可以考虑其它的语言，如AspectJ。 
 
	Spring实现AOP的方法跟其他的框架不同。Spring并不是要提供最完整的AOP实现（尽管Spring AOP有这个能力），
	相反的，它其实侧重于提供一种AOP实现和Spring IoC容器之间的整合，用于帮助解决在企业级开发中的常见问题。 
	
	Spring缺省使用J2SE 动态代理（dynamic proxies）来作为AOP的代理。 这样任何接口（或者接口集）都可以被代理。
	Spring也可以使用CGLIB代理. 对于需要代理类而不是代理接口的时候CGLIB代理是很有必要的。
	如果一个业务对象并没有实现一个接口，默认就会使用CGLIB。