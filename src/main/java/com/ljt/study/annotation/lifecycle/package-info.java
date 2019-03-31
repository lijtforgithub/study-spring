/**
 * @author LiJingTang
 * @version 2019年3月31日 上午10:36:05
 */
package com.ljt.study.annotation.lifecycle;

/**
	Singleton作用域
	当一个bean的作用域为singleton, 那么Spring IoC容器中只会存在一个共享的bean实例，并且所有对bean的请求，只要id与该bean定义相匹配，则只会返回bean的同一实例。
	换言之，当把一个bean定义设置为singlton作用域时，Spring IoC容器只会创建该bean定义的唯一实例。
	这个单一实例会被存储到单例缓存（singleton cache）中，并且所有针对该bean的后续请求和引用都将返回被缓存的对象实例。
	Spring中对单例bean的概念和四人帮（Gang of Four，GoF）设计模式书中定义的单例模式是不同的。GoF的单例硬编码了对象的范围，也就是特定的类仅仅能有一个实例被每一个ClassLoader来创建。
	Spring中单例bean的范围，是对每一个容器和bean来说的。这就意味着在独立的Spring容器中，对一个特定的类创建了一个bean，那么Spring容器通过bean的定义仅仅创建这个类的一个实例。
	
	作为一项规则，对所有有状态的bean使用原型范围，而对于无状态的bean使用单例范围。
	
	和其它范围相比，Spring不管理原型bean的完整生命周期；容器实例化，配置并装配原型对象，并把他给客户端，对于原型bean的实例来说，就没有进一步的跟踪记录了。
	因此，尽管初始化生命周期回调方法可以对所有对象调用而不管范围，那么在原型范围中，配置销毁生命周期回调是不能被调用的。
	客户端代码必须清理原型范围的对象并且释放原型bean持有的系统资源。要让Spring容器来释放原型范围bean持有的资源，可以使用自定义bean后处理器，它持有需要被清理的bean的引用。
	在某些方面，关于原型范围bean的Spring容器的角色就是对Java new操作符的替代。所有之后生命周期的管理必须由客户端来处理。
	
	当你使用单例范围的bean和其依赖是原型范围的bean时，要当心依赖是在实例化时被解析的。
	因此，如果依赖注入了原型范围的bean到单例范围的bean中，新的原型bean就被实例化并且依赖注入到单例bean中。原型实例是唯一的实例并不断提供给单例范围的bean。
	假设你想单例范围的bean在运行时可以重复获得新的原型范围bean的实例。那么就不能将原型范围的bean注入到单例bean中，因为这个注入仅仅发生一次，
	就是在Spring容器实例化单例bean并解析和注入它的依赖时。如果你在运行时需要原型bean新的实例而不是仅仅一次，请参考方法注入。
	
	
	
	顶层<beans/>元素的default-init-method属性的存在，就会让Spring的IoC容器意识到在bean中的一个叫做init的方法就是初始化回调方法。
	当一个bean被创建和装配时，如果bean类有这样一个方法，那么它就会在合适的时间被调用。
	相似地（也就是在XML中），你可以使用顶层元素<beans/>的default-destroy-method属性来配置销毁回调方法。
	在已经存在的bean类中，有命名差异的回调方法，你可以指定（也就是在XML中）<bean/>本身的init-method和destroy-method属性来覆盖默认的方法。
	
	注意Spring容器保证在bean的所有依赖都满足后立即执行配置的初始化回调。这意味着初始化回调在原生bean上调用，
	这也意味着这个时候任何诸如AOP拦截器之类的将不能被应用。一个目标bean是首先完全创建，然后才应用诸如AOP代理等拦截器链。
	注意，如果目标bean和代理是分开定义了，你的代码甚至可以绕开代理直接和原生bean通信。因此，在初始化方法上使用拦截器将产生未知的结果，
	因为这将目标bean和它的代理/拦截器的生命周期绑定并且留下了和初始bean直接通信这样奇怪的方式。
	
	调用顺序：
	@PostConstruct
	InitializingBean的afterPropertiesSet()
	自定义init()方法
	@PreDestroy
	DisposableBean的destroy()
	自定义destroy()方法
	
 */