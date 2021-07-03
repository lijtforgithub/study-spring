#### 概念
OOP中模块化的关键单元是类（classes），AOP中模块化的单元则是切面。

- 通知: 许多AOP框架（包括Spring）都是以拦截器做通知模型，并维护一个以连接点为中心的拦截器链。
- 目标对象: 既然Spring AOP是通过运行时代理实现的，这个对象永远是一个被代理（proxied）对象。在Spring中，AOP代理可以是JDK动态代理或者CGLIB代理。
- 切入点表达式如何和连接点匹配是AOP的核心：Spring缺省使用AspectJ切入点语法。Spring和其他纯Java AOP框架一样，在运行时完成织入。
- Spring目前仅支持使用方法调用作为连接点（join point）（在Spring bean上通知方法的执行）。虽然可以在不影响到Spring AOP核心API的情况下加入对成员变量拦截器支持，但Spring并没有实现成员变量拦截器。如果你需要把对成员变量的访问和更新也作为通知的连接点，可以考虑其它的语言，如AspectJ。
 
Spring实现AOP的方法跟其他的框架不同。Spring并不是要提供最完整的AOP实现（尽管Spring AOP有这个能力），相反的，它其实侧重于提供一种AOP实现和Spring IoC容器之间的整合，用于帮助解决在企业级开发中的常见问题。
	
Spring缺省使用J2SE 动态代理（dynamic proxies）来作为AOP的代理。 这样任何接口（或者接口集）都可以被代理。Spring也可以使用CGLIB代理. 对于需要代理类而不是代理接口的时候CGLIB代理是很有必要的。如果一个业务对象并没有实现一个接口，默认就会使用CGLIB。


#### @AspectJ
为了在Spring配置中使用@AspectJ切面，你首先必须启用Spring对@AspectJ切面配置的支持，并确保自动代理（autoproxying）的bean是否能被这些切面通知。自动代理是指Spring会判断一个bean是否使用了一个或多个切面通知，并据此自动生成相应的代理以拦截其方法调用，并且确保通知在需要时执行。启用@AspectJ支持后，在application context中定义的任意带有一个@Aspect切面（拥有@Aspect注解）的bean都将被Spring自动识别并用于配置Spring AOP。

切面（用@Aspect注解的类）和其他类一样有方法和字段定义。他们也可能包括切入点，通知和引入（inter-type）声明。 在Spring AOP中，拥有切面的类本身不可能是其它切面中通知的目标。一个类上面的@Aspect注解标识它为一个切面，并且从自动代理中排除它。用更少的命名组件来构建更加复杂的切入点表达式是一种最佳实践。当用名字来指定切入点时使用的是常见的Java成员可视性访问规则。（比如说，你可以在同一类型中访问私有的切入点，在继承关系中访问受保护的切入点，可以在任意地方访问公共切入点）。成员可视性访问规则不影响到切入点的匹配。
	
注意'bean' PCD仅仅 被Spring AOP支持而不是AspectJ. 这是Spring对AspectJ中定义的标准PCD的一个特定扩展。

```
execution([modifiers-pattern] ret-type-pattern [declaring-type-pattern] name-pattern(param-pattern) [throws-pattern])

除了返回类型模式（上面代码片断中的ret-type-pattern），名字模式和参数模式以外， 所有的部分都是可选的。
返回类型模式决定了方法的返回类型必须依次匹配一个连接点。 你会使用的最频繁的返回类型模式是*，它代表了匹配任意的返回类型。
一个全限定的类型名将只会匹配返回给定类型的方法。名字模式匹配的是方法名。 你可以使用*通配符作为所有或者部分命名模式。 
参数模式稍微有点复杂：()匹配了一个不接受任何参数的方法， 而(..)匹配了一个接受任意数量参数的方法（零或者更多）。 
模式(*)匹配了一个接受一个任何类型的参数的方法。 模式(*,String)匹配了一个接受两个参数的方法，第一个可以是任意类型， 第二个则必须是String类型。
```
#### 通知顺序
Spring AOP遵循跟AspectJ一样的优先规则来确定通知执行的顺序。 在进入连接点的情况下，最高优先级的通知会先执行（所以给定的两个前置通知中，优先级高的那个会先执行）。 在退出连接点的情况下，最高优先级的通知会最后执行。（所以给定的两个后置通知中， 优先级高的那个会第二个执行）。

当定义在不同的切面里的两个通知都需要在一个相同的连接点中运行， 那么除非你指定，否则执行的顺序是未知的。你可以通过指定优先级来控制执行顺序。 在标准的Spring方法中可以在切面类中实现org.springframework.core.Ordered 接口
或者用Order注解做到这一点。在两个切面中， Ordered.getValue()方法返回值（或者注解值）较低的那个有更高的优先级。

当定义在相同的切面里的两个通知都需要在一个相同的连接点中运行， 执行的顺序是未知的（因为这里没有方法通过反射javac编译的类来获取声明顺序）。考虑在每个切面类中按连接点压缩这些通知方法到一个通知方法，或者重构通知的片段到各自的切面类中 - 它能在切面级别进行排序。

Spring AOP部分使用JDK动态代理或者CGLIB来为目标对象创建代理。（建议优先使用JDK的动态代理）如果被代理的目标对象实现了至少一个接口，则会使用JDK动态代理。所有该目标类型实现的接口都将被代理。 若该目标对象没有实现任何接口，则创建一个CGLIB代理。 如果你希望强制使用CGLIB代理，（例如：希望代理目标对象的所有方法，而不只是实现自接口的方法） 那也可以。但是需要考虑以下问题:
无法通知（advise）Final方法，因为他们不能被覆写。你需要将CGLIB二进制发行包放在classpath下面，与之相较JDK本身就提供了动态代理。 当需要CGLIB而在classpath下又没有找到CGLIB类库的话，Spring会自动提醒。
代理对象的构造器会被调用两次。这是很自然的结果因为在CGLIB代理模式下每一个代理对象都会 产生一个子类。每一个代理实例会生成两个对象：实际代理对象和它的一个实现了通知的子类实例 而是用JDK代理时不会出现这样的行为。
通常情况下，调用代理类型的构造器两次并不是问题， 因为除了会发生指派外没有任何真正的逻辑被实现。
```
强制使用CGLIB代理需要将<aop:config>的proxy-target-class 属性设为true:
<aop:config proxy-target-class="true"></aop:config>
当使用@AspectJ自动代理时要强制使用CGLIB，请将<aop:aspectj-autoproxy> 的proxy-target-class属性设置为true:
<aop:aspectj-autoproxy proxy-target-class="true" />

多个<aop:config/>片段在运行时被包含到一个统一的自动代理构造器中， 它为任何<aop:config/>片段（一般来自不同的XML bean定义文件）中指定的内容应用 最强的代理设置。
此设置同样也适用于<tx:annotation-driven/> 和<aop:aspectj-autoproxy/>元素。
清楚地讲，在<tx:annotation-driven/>、 <aop:aspectj-autoproxy/>或者<aop:config/> 元素上使用'proxy-target-class="true"'会导致将CGLIB代理应用于此三者之上。
```
#### Spring AOP是基于代理机制的。最后，必须注意AspectJ不存在这种自我调用的问题，因为它并不是一个基于代理的AOP框架。