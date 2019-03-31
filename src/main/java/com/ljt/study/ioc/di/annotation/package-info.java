/**
 * @author LiJingTang
 * @version 2015年8月26日 下午6:22:36
 */
package com.ljt.study.ioc.di.annotation;

/**
	使用@Autowired、@Inject注解，需要在Spring容器中声明AutowiredAnnotationBeanPostProcessor Bean。
	传统的声明方式：<bean class="org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor" />
	使用@PersistenceContext注解，需要在Spring容器中声明PersistenceAnnotationBeanPostProcessor Bean。
	传统的声明：<bean class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor" />
	使用@Required注解，需要在Spring容器中声明RequiredAnnotationBeanPostProcessor Bean。
	传统声明方式： <bean class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor" />
	使用@Resource、@ PostConstruct、@ PreDestroy等注解就必须声明CommonAnnotationBeanPostProcessor。
	传统申明方式： <bean class="org.springframework.beans.factory.annotation.CommonAnnotationBeanPostProcessor" />
	基础的bean的定义都是在XML文件中来明确定义的，而注解仅仅进行依赖注入。
	<context:annotation-config /> 隐式注册的后处理器包含AutowiredAnnotationBeanPostProcessor，CommonAnnotationBeanPostProcessor，
	PersistenceAnnotationBeanPostProcessor，RequiredAnnotationBeanPostProcessor
	<context:annotation-config />仅能够在已经在已经注册过的bean上面起作用。对于没有在spring容器中注册的bean，它并不能执行任何操作。
	<context:component-scan>除了具有<context:annotation-config />的功能之外，还具有自动将带有@component,@service,@Repository等注解的对象注册到spring容器中的功能。
	
	<context:annotation-config/>仅仅查找定义在同一上下文中的bean的注解。这就意味着，如果你为DispatcherServlet将<context:annotation-config/>
	放置在WebApplicationContext中，那么它仅仅检查控制器中的@Autowired bean，而不是你的服务层bean。
	
	@Autowired和@Inject的报错信息完全相同，他们都是通过 AutowiredAnnotationBeanPostProcessor类实现的依赖注入，二者具有可互换性。 
	@Resource通过CommonAnnotationBeanPostProcessor类实现依赖注入
	
	@Autowired，@Inject，@Resource和@Value注解是由Spring的BeanPostProcessor实现类来控制的，反过来告诉你你不能在BeanPostProcessor
	或BeanFactoryPostProcessor类型（任意之一）应用这些注解。这些类型必须明确地通过XML或使用Spring的@Bean方法来装配。
	
	Spring annotations VS standard annotations

	Spring					javax.inject.*			javax.inject restrictions / comments
	
	@Autowired				@Inject					@Inject 没有 'required'属性
 	@Component				@Named					-
 	@Scope("singleton")		@Singleton				JSR-330的默认范围是Spring’s prototype。但是，为了保持与Spring的一般默认一致的，
 													在Spring容器中声明的JSR-330 bean是一个singleton。为了使用范围上比单等，应使用Spring的@Scope注解。
 													javax.inject还提供了@Scope注解。尽管如此，这个是用于代替创建自己的注解。
 	@Qualifier				@Named					-
	@Value					-						-
	@Required				-						-
	@Lazy					-						-
	
 */