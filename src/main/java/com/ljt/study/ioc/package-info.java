/**
 * @author LiJingTang
 * @version 2015年8月22日 下午3:32:05
 */
package com.ljt.study.ioc;

/**
 	IOC-控制反转 DI-依赖注入
 	这是一个对象定义它们依赖的过程，也就是说，它们使用的其它对象，仅仅通过构造方法参数，工厂方法参数或在对象被创建后的实例上设置的属性，
 	亦或者是从工厂方法返回的参数。之后容器在它创建bean的时候注入那些依赖。这个过程是根本上的反向，因此名称是控制反转。
 	
 	BeanFactory接口提供高级的配置机制，可以管理任意类型的对象。
 	ApplicationContext是BeanFactory的子接口。它添加了和Spring的AOP特性很简便的整合；消息资源处理（用于国际化i18n），事件发布；应用层特定的上下文，比如用于Web应用程序的WebApplicationContext。
 	总之，BeanFactory提供了配置框架和基本功能，而ApplicationContext添加了更多企业级开发特定的功能。ApplicationContext是BeanFactory完整的超集。
 	
 	在Spring中，对象构成应用程序的骨感，它们是由Spring的IoC容器管理的，并被称为bean。一个bean就是一个实例化并组装的对象，由Spring的IoC容器来管理。
 	ApplicationContext接口代表了Spring的IoC容器，负责实例化，配置和装配上述的bean。容器获得指示来实例化某对象，配置并装配，这都是通过读取配置元数据实现的。
 	
 	配置元数据在XML中，Java注解或Java代码中来表示。ApplicationContext接口的一些实现使用Spring开箱的支持。
 	在独立的应用程序中，通常是来创建ClassPathXmlApplicationContext或FileSystemXmlApplicationContext的实例。
 	XML是定义配置元数据的传统格式，你可以指示容器使用Java的注解或是代码作为元数据的格式，提供少量的XML配置声明来开启对这些额外的元数据格式的支持。
 	
 	
 	BeanFactory 还是 ApplicationContext?
 	简单的说：除非你有更好的理由，否则尽量使用ApplicationContext，下面是对于哪些"为什么"等等更深入的建议。
	ApplicationContext包含BeanFactory的所有功能。通常建议比BeanFactory优先，除非有一些限制的场合如字节长度对内存有很大的影响时（Applet）。
	然后，绝大多数"典型的"企业应用和系统，ApplicationContext就是你需要使用的。Spring2.0及以上版本，
	大量使用了link linkend="beans-factory-extension-bpp">BeanPostProcessor扩展（以便应用代理等功能），
	如果你选择BeanFactory则无法使用相当多的支持功能，如事务和AOP，这可能会导致混乱，因为配置并没有错误。
	
	
	PropertyPlaceholderConfigurer不仅仅查看在Properties文件中指定的属性。默认情况下，如果它不能在指定的属性文件中发现属性，它也会检查Java System属性。
	你可以通过设置systemPropertiesMode属性，使用下面整数的三者之一来自定义这种行为：
  never(0)：从不检查系统属性
  fallback(1)：如果没有在指定的属性文件中解析到属性，那么就检查系统属性。这是默认的情况。
  override(2)：在检查指定的属性文件之前，首先去检查系统属性。这就允许系统属性覆盖其它任意的属性资源。

 */