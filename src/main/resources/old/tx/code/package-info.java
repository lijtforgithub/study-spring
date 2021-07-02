/**
 * @author LiJingTang
 * @version 2015年9月23日上午8:50:28
 */
package com.ljt.study.transaction;

/**
 	传统上，J2EE开发者有两个事务管理的选择： 全局 或 本地 事务。
 	全局事务由应用服务器管理，使用JTA。局部事务是和资源相关的，比如一个和JDBC连接关联的事务。
 	全局事务可以用于多个事务性的资源（典型例子是关系数据库和消息队列）。
 	使用局部事务，应用服务器不需要参与事务管理，并且不能帮助确保跨越多个资源（需要指出的是多数应用使用单一事务性的资源）的事务的正确性。
 	
 	全局事务.  全局事务有一个重大的缺陷，代码需要使用JTA：一个笨重的API（部分是因为它的异常模型）。
 	此外，JTA的UserTransaction通常需要从JNDI获得，这意味着我们为了JTA，需要 同时 使用JNDI 和 JTA。
 	显然全部使用全局事务限制了应用代码的重用性，因为JTA通常只在应用服务器的环境中才能使用。 
 	以前，使用全局事务的首选方式是通过EJB的 CMT（容器管理事务）：CMT是 声明式事务管理 的一种形式（区别于 编程式事务管理）。
 	EJB的CMT不需要任何和事务相关的JNDI查找，虽然使用EJB本身肯定需要使用JNDI。它消除了大多数（不是全部）硬编码的方式去控制事务。
 	重大的缺陷是CMT绑定在JTA和应用服务器环境上，并且只有我们选择使用EJB实现业务逻辑，或者至少处于一个事务化EJB的外观（Facade）后才能使用它。
 	EJB有如此多的诟病，尤其是存在其它声明式事务管理时，EJB不是一个吸引人的建议。

	本地事务. 本地事务容易使用，但也有明显的缺点：它们不能用于多个事务性资源。
	例如，使用JDBC连接事务管理的代码不能用于全局的JTA事务中。另一个缺点是局部事务趋向于入侵式的编程模型。


	
	Connection conn = DataSourceUtils.getConnection(dataSource);
	如果已有一个事务及与之关联的connection存在，该实例将被返回。
	否则，该方法调用将触发起一个新的connection的创建动作，该connection（可选地）被同步到任何现有的事务，并可以在同一事务范围内被后续的调用复用。
	这个过程有一个额外的好处，就是任何 SQLException将被包装为Spring框架的 CannotGetJdbcConnectionException，该类是Spring框架的unchecked的DataAccessExceptions层次体系中的一员。
	这将给你比从 SQLException 中简单所得更多的信息，而且保证了跨数据库——甚至其他持久化技术——的移植性。
	
	默认式Spring处理声明式事务管理的规则遵守EJB习惯（只在遇到unchecked exceptions时自动回滚），但通常定制这条规则会更有用。
	Spring的事务管理是通过AOP代理实现的。 其中的事务通知由元数据（目前基于XML或注解）驱动。 
	代理对象与事务元数据结合产生了一个AOP代理，它使用一个PlatformTransactionManager 实现品配合TransactionInterceptor，在方法调用前后实施事务。

	Spring框架的事务基础架构代码将默认地 只 在抛出运行时和unchecked exceptions时才标识事务回滚。
	也就是说，当抛出一个 RuntimeException 或其子类例的实例时。（Errors 也一样 - 默认地 - 标识事务回滚。）
	从事务方法中抛出的Checked exceptions将 不 被标识进行事务回滚。
	 
	编程式 方式来指定回滚事务: TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	 
	 
	 
	@Transactional 注解可以被应用于接口定义和接口方法、类定义和类的 public 方法上。 然而，请注意只是使用 @Transactional 注解并不会启用事务行为，
	它仅仅 是一种元数据，能够被可以识别 @Transactional 注解和上述的配置适当的具有事务行为的beans所使用。其实正是 <tx:annotation-driven/>元素的出现 开启 了事务行为。
	 
	方法的可见度和 @Transactional
	在使用代理的时候，@Transactional 注解应该只被应用到 public 可见度的方法上。 如果你在 protected、private 或者 package-visible 的方法上使用 @Transactional 注解，
	系统也不会报错， 但是这个被注解的方法将不会执行已配置的事务设置。如果你非要注解非公共方法的话，请参考使用AspectJ。
	
	Spring团队的建议是你只在具体的类上使用 @Transactional 注解， 而不要注解在接口上。你当然可以在接口（或接口方法）上使用 @Transactional 注解， 
	但是这只有在你使用基于接口的代理时它才会生效。因为注解是 不能继承 的， 这就意味着如果你正在使用基于类的代理时，事务的设置将不能被基于类的代理所识别，
	而且对象也不会被事务代理所包装 （这是很糟糕的）。 因此，请接受Spring团队的建议，在具体的类（包括该类的方法）上使用 @Transactional 注解。 
	
	注意：在代理模式下（默认的情况），只有从代理传过来的外部方法调用才会被拦截。 这就意味着自我调用是不会触发事务的，
	比如说，一个在目标对象中调用目标对象其他方法的方法是不会触发一个事务的，即使这个方法被标记为 @Transactional!

	如果你期望自我调用被事务覆盖到，可以考虑使用AspectJ 模式（如下所示）。在这种情况下，一开始就没有任何代理的存在； 
	为了把@Transactional的方法变成运行时的行为，目标类会被编织起来（比如修改它的字节码）。
	
	proxy-target-class 只对代理模式有效。决定为那些使用了@Transactional注解的类创建何种事务代理。 如果 "proxy-target-class" 属性被设为 "true"， 那么基于类的代理就会被创建。
	如果 "proxy-target-class"属性被设为"false" 或者没设，那么基于接口的标准JDK代理就会被创建。在多数情形下，方法的事务设置将被优先执行。
	

	当事务传播被设置PROPAGATION_REQUIRED的时候， 会为每一个被应用到的方法创建一个逻辑事务作用域。 每一个这样的逻辑事务作用域都可以自主地决定rollback-only状态，
	当这样的逻辑事务作用域被外部的一个逻辑事务作用域所包含的时候， 他们在逻辑上是独立的。当然了，对于正常的 PROPAGATION_REQUIRED设置来说，他会被映射到相同的物理事务上。 
	所以一个标记有rollback-only的内部逻辑事务作用域的确会影响到外部的逻辑事务作用域（就像你所预料的那样）。 
	然而，当内部的事务作用域标记为rollback-only，同时外部的事务作用域并没有决定要回滚， 这样的回滚是意料不到的（静悄悄地由内部事务作用域触发的）： 
	一个对应的UnexpectedRollbackException 异常会在这个时候被抛出。这是 可以预料到的行为， 只有这样，这个事务的调用者才不会被误导，在事务没有提交的情况下误以为事务已经提交。
	所以如果内部的事务（外部的调用者并不知情）标记该事务为 rollback-only，而外部的调用者却依旧在不知情的情况下提交后，
	它需要收到一个 UnexpectedRollbackException 异常来清楚的了解事务并没有提交而是发生了回滚。 
	
	PROPAGATION_REQUIRES_NEW，与之前相反，为每一个相关的事务作用域使用了一个完全 独立的事务。
	在这种情况下，物理事务也将是不同的，因此外部事务可以不受内部事务回滚状态的影响独立提交或者回滚。
	
	PROPAGATION_NESTED 是一个完全不同的设置。它使用了一个单独的物理事务， 这个事务拥有多个可以回滚的保存点。这样部分回滚允许内部事务在它的作用域内触发一个回滚， 并且外部事务能够不受影响的继续。
	这通常是对应于JDBC的保存点，所以只会在 JDBC 资源事务管理上起效 
	 
	 
	 
	 
	结合AspectJ使用 @Transactional
	// construct an appropriate transaction manager 
	DataSourceTransactionManager txManager = new DataSourceTransactionManager(getDataSource());
	// configure the AnnotationTransactionAspect to use it; this must be done before executing any transactional methods
	AnnotationTransactionAspect.aspectOf().setTransactionManager(txManager); 
 */