> 注解注入会在XML注入之前执行，因此通过两种方式，那么后面的配置会覆盖前面装配的属性。

#### IOC-控制反转 DI-依赖注入
依赖注入（DI）是对象定义它们依赖的过程，也就是说，要和它们协同工作其它对象，仅仅可以通过构造方法参数，工厂方法参数，或者是在工厂方法返回的对象或被构造好后，为对象实例设置的属性。容器当创建好bean，随后就会注入那些依赖。这个过程从根本上来说是反向的，因此命名为控制反转（IoC），bean本身直接使用构造好的类或服务定位器模式来控制实例或它的依赖的所在位置。
> IOC是思想 DI是手段
#### BeanFactory&ApplicationContext
BeanFactory接口提供高级的配置机制，可以管理任意类型的对象。
ApplicationContext是BeanFactory的子接口。它添加了和Spring的AOP特性很简便的整合；消息资源处理（用于国际化i18n），事件发布；应用层特定的上下文，比如用于Web应用程序的WebApplicationContext。
总之，BeanFactory提供了配置框架和基本功能，而ApplicationContext添加了更多企业级开发特定的功能。ApplicationContext是BeanFactory完整的超集。

在Spring中，对象构成应用程序的骨感，它们是由Spring的IoC容器管理的，并被称为bean。一个bean就是一个实例化并组装的对象，由Spring的IoC容器来管理。ApplicationContext接口代表了Spring的IoC容器，负责实例化，配置和装配上述的bean。容器获得指示来实例化某对象，配置并装配，这都是通过读取配置元数据实现的。

 配置元数据在XML中，Java注解或Java代码中来表示。ApplicationContext接口的一些实现使用Spring开箱的支持。在独立的应用程序中，通常是来创建ClassPathXmlApplicationContext或FileSystemXmlApplicationContext的实例。XML是定义配置元数据的传统格式，你可以指示容器使用Java的注解或是代码作为元数据的格式，提供少量的XML配置声明来开启对这些额外的元数据格式的支持。

 BeanFactory 还是 ApplicationContext?  
简单的说：除非你有更好的理由，否则尽量使用ApplicationContext，下面是对于哪些"为什么"等等更深入的建议。ApplicationContext包含BeanFactory的所有功能。通常建议比BeanFactory优先，除非有一些限制的场合如字节长度对内存有很大的影响时（Applet）。然后，绝大多数"典型的"企业应用和系统，ApplicationContext就是你需要使用的。
Spring2.0及以上版本，	大量使用了link `<linkend="beans-factory-extension-bpp">` BeanPostProcessor扩展（以便应用代理等功能），
如果你选择BeanFactory则无法使用相当多的支持功能，如事务和AOP，这可能会导致混乱，因为配置并没有错误。
#### PropertyPlaceholderConfigurer
PropertyPlaceholderConfigurer不仅仅查看在Properties文件中指定的属性。默认情况下，如果它不能在指定的属性文件中发现属性，它也会检查Java System属性。你可以通过设置systemPropertiesMode属性，使用下面整数的三者之一来自定义这种行为：
- never(0)：从不检查系统属性
- fallback(1)：如果没有在指定的属性文件中解析到属性，那么就检查系统属性。这是默认的情况。
- override(2)：在检查指定的属性文件之前，首先去检查系统属性。这就允许系统属性覆盖其它任意的属性资源。

## Bean
#### 命名Bean
每个bean有一个或者多个标识符。这些标识符必须在托管bean的容器内唯一。通常一个bean只有一个标识符，但如果它需要多个的话，其它的可以被认为是别名。在基于XML的配置元数据中，你可以使用id和/或name属性来指定bean的标识符。id属性允许你指定一个准确的id。可能是特殊字符。

如果你想为bean引入别名，你也可以在name属性中来指定，通过逗号（,），分号（;）或空格来分隔。当然你也可以不给bean提供name或id。如果没有提供明确的name或id，那么容器会为这个bean生成一个唯一的名称。	然而，如果你想通过名称来参考这个bean，那么可以使用ref元素或者是服务定位器风格的查找，你必须提供一个名称。不提供名称的动机是和使用内部bean或自动装备合作者相关的。
#### 实例化Bean
 bean的定义信息本质上是创建一个或多个对象的方子。当需要一个bean时，容器查找这些方子来查找命名的bean，并且使用由bean定义信息封装的配置元数据来创建（或获得）一个真实的对象。  
 和使用静态工厂方法实例化相似，使用实例工厂方法实例化是要调用容器中已有bean的一个非静态的方法来创建新的bean。要使用这个机制，请把class属性留空，但在factory-bean属性中指定当前（或父/祖先）容器中bean的名字，该bean要包含被调用来创建对象的实例方法。使用factory-method方法来设置工厂方法的名称。

内部bean的定义不需要定义id或name；容器忽略这些值。也会忽略scope标识。
内部bean通常是匿名的，而且范围通常是prototype的。注入内部bean到协作bean是不可能的，只能到包围它的bean中。
#### Singleton作用域
当一个bean的作用域为singleton, 那么Spring IoC容器中只会存在一个共享的bean实例，并且所有对bean的请求，只要id与该bean定义相匹配，则只会返回bean的同一实例。换言之，当把一个bean定义设置为singleton作用域时，Spring IoC容器只会创建该bean定义的唯一实例。这个单一实例会被存储到单例缓存（singleton cache）中，并且所有针对该bean的后续请求和引用都将返回被缓存的对象实例。

Spring中对单例bean的概念和四人帮（Gang of Four，GoF）设计模式书中定义的单例模式是不同的。GoF的单例硬编码了对象的范围，也就是特定的类仅仅能有一个实例被每一个ClassLoader来创建。Spring中单例bean的范围，是对每一个容器和bean来说的。这就意味着在独立的Spring容器中，对一个特定的类创建了一个bean，那么Spring容器通过bean的定义仅仅创建这个类的一个实例。

作为一项规则，对所有有状态的bean使用原型范围，而对于无状态的bean使用单例范围。和其它范围相比，Spring不管理原型bean的完整生命周期；容器实例化，配置并装配原型对象，并把他给客户端，对于原型bean的实例来说，就没有进一步的跟踪记录了。因此，尽管初始化生命周期回调方法可以对所有对象调用而不管范围，那么在原型范围中，配置销毁生命周期回调是不能被调用的。

客户端代码必须清理原型范围的对象并且释放原型bean持有的系统资源。要让Spring容器来释放原型范围bean持有的资源，可以使用自定义bean后处理器，它持有需要被清理的bean的引用。在某些方面，关于原型范围bean的Spring容器的角色就是对Java new操作符的替代。所有之后生命周期的管理必须由客户端来处理。

当你使用单例范围的bean和其依赖是原型范围的bean时，要当心依赖是在实例化时被解析的。因此，如果依赖注入了原型范围的bean到单例范围的bean中，新的原型bean就被实例化并且依赖注入到单例bean中。原型实例是唯一的实例并不断提供给单例范围的bean。假设你想单例范围的bean在运行时可以重复获得新的原型范围bean的实例。那么就不能将原型范围的bean注入到单例bean中，因为这个注入仅仅发生一次，就是在Spring容器实例化单例bean并解析和注入它的依赖时。如果你在运行时需要原型bean新的实例而不是仅仅一次，请参考方法注入。

顶层`<beans/>`元素的default-init-method属性的存在，就会让Spring的IoC容器意识到在bean中的一个叫做init的方法就是初始化回调方法。当一个bean被创建和装配时，如果bean类有这样一个方法，那么它就会在合适的时间被调用。相似地（也就是在XML中），你可以使用顶层元素`<beans/>`的default-destroy-method属性来配置销毁回调方法。在已经存在的bean类中，有命名差异的回调方法，你可以指定（也就是在XML中）`<bean/>`本身的init-method和destroy-method属性来覆盖默认的方法。

注意Spring容器保证在bean的所有依赖都满足后立即执行配置的初始化回调。  
这意味着初始化回调在原生bean上调用，这也意味着这个时候任何诸如AOP拦截器之类的将不能被应用。
一个目标bean是首先完全创建，然后才应用诸如AOP代理等拦截器链。注意，如果目标bean和代理是分开定义了，你的代码甚至可以绕开代理直接和原生bean通信。因此，在初始化方法上使用拦截器将产生未知的结果，因为这将目标bean和它的代理/拦截器的生命周期绑定并且留下了和初始bean直接通信这样奇怪的方式。
调用顺序：
1. @PostConstruct
2. InitializingBean的afterPropertiesSet()
3. 自定义init()方法
4. @PreDestroy
5. DisposableBean的destroy()
6. 自定义destroy()方法

## 依赖注入
1. 基于构造方法的依赖注入  
基于构造方法的依赖注入是容器调用构造方法和一组参数完成的，每个都表示着一个依赖。
- 构造方法参数解析  
构造方法参数解析匹配使用参数的类型。如果在bean的构造方法参数不存在潜在的歧义，那么当bean被实例化的时候，定义的构造方法参数的顺序就是被提供到适当构造方法参数的顺序。当使用简单类型时，比如`<value>true<value>`，Spring不能决定值的类型，所以没有帮助是不能匹配的。
- 构造方法参数名称  
要记住要使得这种方式可用，代码必须和内嵌的调试标识一起编译，那样Spring才可以从构造方法中来查找参数。如果没有和调试标识（或不想）一起编译，那么可以使用JDK的注解@ConstructorProperties来明确构造方法参数。
2. 基于setter方法的依赖注入  
基于setter方法的依赖注入由容器在调用过无参数的构造方法或无参数的static工厂方法来实例化bean之后，再调用bean的setter方法来完成的。
ApplicationContext对它管理的bean支持基于构造方法和setter方法的依赖注入。也支持在一些依赖已经通过构造方法注入之后再进行setter方法注入。  
以BeanDefinition形式来配置依赖，使用PropertyEditor实例将属性从一种形式格式化到另一种。但很多Spring的用户不直接（编程时）使用这些类，而是使用XML文件来定义，之后会在内部转换这些类的实例，需要加载整个Spring IoC容器的实例。

#### 基于构造方法还是setter方法进行依赖注入？
有一个很好的规则就是为强制依赖使用构造方法参数，而对于可选参数使用setter方法。  
要注意在setter方法上使用注解@Required，可用于setter方法所需的依赖。Spring团队通常主张使用setter方法注入，因为大量的构造方法参数会使程序变得非常笨拙，特别是当属性为可选的时候。setter方法会让该类的对象今后适合于重新配置或重新注入。

可以通过setter方法注入来配置循环依赖（A需要通过构造方法注入获得类B的实例，而类B也需要通过构造方法注入获得类A的实例。如果把类A和类B进行相互注入，Spring的IoC容器会在运行时检测到这是循环引用的情况，并且抛出BeanCurrentlyInCreationException异常。）。

容器按如下步骤来解决bean的依赖：
1. ApplicationContext和描述了所有bean的配置元数据一起被创建并初始化。配置元数据可以通过XML，Java代码或注解来指定。
2. 对于每一个bean来说，它的依赖被表述为属性，构造方法参数的形式，如果你使用了静态工厂方法来代替构造方法，那么还会是静态工厂方法参数的形式。当bean被实际创建时，这些依赖被提供给bean。
3. 每个属性或构造方法参数就是一个要被设置的值或者是容器中其它bean的引用。
4. 每个属性或构造方法参数值会被转换特定格式的形式，去匹配属性或构造方法参数的类型。默认情况下，Spring可以转换给定的字符串格式的值到内建的类型，比如int，long，String，boolean等。当容器被创建时，Spring容器会来验证每个bean的配置，包括验证bean的引用属性是否是一个合法的bean。然而，bean属性本身直到bean真正被创建出来后才被设置进去。

    如果没有循环依赖的存在，当一个或多个协作的bean被注入到一个独立的bean时，每个协作的bean就会在被注入之前完全被配置好。这就意味着如果bean A对bean B有依赖，那么Spring的IoC容器会完全配置bean B而优先于调用bean A中的setter方法。
#### 集合
abstract="true" 使用abstract属性显式地将父bean定义标记为抽象的。由于这样的父bean是不完整的，而且还被显式标记为抽象的，因而它无法得到自己的实例。抽象bean定义可作为子bean定义的模板。若要尝试单独使用这样的父bean（比如将它作为其他bean的ref属性而引用，或者直接使用这个父bean的id作为参数调用getBean()方法），将会导致错误。

在`<list/>`元素特定的情形下，和List集合类型相关的语义，也就是说，ordered集合值的概念，是要维护的；父值优先于所有子list的值。在Map，Set和Properties集合类型的情况下，没有顺序的存在。因此对于容器内部使用的，和Map，Set和Properties实现类型相关的集合类型没有排序语义的作用。

集合合并的限制:不能合并不同类型的集合（比如Map和List），如果你要尝试这么去做，那么就会抛出Exception。merge属性必须在低级的，继承的，子bean中来指定；在父集合中指定merge属性是冗余的，也不会看到想要的合并结果。
#### 自动装配协作者
自动装配可以显著减少指定属性或构造方法参数的需要。  
自动装配可以更新配置对象的演变。比如，如果你需要给一个类添加依赖，那个依赖可以自动被填入而不需要修改配置。因此，自动装配在开发期间是非常有用的，当代码库变得更稳定时切换到明确的装配也没有否定的选择。
1. no：（default）没有自动装配  
Bean的引用必须通过ref元素来定义。对于大型的部署，修改默认设置是不推荐的，因为明确地指定协作者会给予更多的控制和清晰。某种程度上来说，它勾勒出了系统的结构。
2. byName：通过属性名称来自动装配  
Spring以相同名称来查找需要被自动装配的bean。比如，如果bean被设置成由名称来自动装配，并含有一个master属性（也就说，有setMaster(..)方法），Spring会查找名为master的bean定义，并且用它来设置属性。
3. byType：如果bean的属性类型在容器中存在的话，就允许属性被自动装配。如果存在多于一个，就会抛出致命的异常，这就说明了对那个bean不能使用byType进行自动装配。如果没有匹配的bean存在，就不会有任何效果；属性不会被设置。
4. constructor：和byType类似，但是是应用于构造方法参数的。如果在容器中没有确定的构造方法参数类型的bean的存在，就会发生致命的错误。

从自动装配中排除bean  
在每个bean的基础上，你可以从自动装配中来排除bean。在Spring的XML格式配置中，设置`<bean/>`元素的autowire-candidate属性为false；容器会把指定的bean对自动装配不可用（包含注解风格的配置，比如@Autowired）。你也可以基于bean名称的模式匹配来限制自动装配候选者。  
顶级的`<beans/>`元素中的default-autowire-candidates属性接受一个或多个模式。这并不意味着未包含的bean不能使用自动装配来配置。相反，bean本身不是自动装配其它bean的候选者。

- 使用@Autowired、@Inject注解，需要在Spring容器中声明AutowiredAnnotationBeanPostProcessor Bean。  
传统的声明方式：`<bean class="org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor" />`
- 使用@PersistenceContext注解，需要在Spring器中声明PersistenceAnnotationBeanPostProcessor Bean。  
传统的声明：`<bean class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor" />`
- 使用@Required注解，需要在Spring容器中声明RequiredAnnotationBeanPostProcessor Bean。  
传统声明方式： `<bean class="org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor" />`
- 使用@Resource、@ PostConstruct、@ PreDestroy等注解就必须声明CommonAnnotationBeanPostProcessor。  
传统申明方式： `<bean class="org.springframework.beans.factory.annotation.CommonAnnotationBeanPostProcessor" />`

基础的bean的定义都是在XML文件中来明确定义的，而注解仅仅进行依赖注入。

`<context:annotation-config />`隐式注册的后处理器包含AutowiredAnnotationBeanPostProcessor，CommonAnnotationBeanPostProcessor，PersistenceAnnotationBeanPostProcessor，RequiredAnnotationBeanPostProcessor

`<context:annotation-config />`仅能够在已经在已经注册过的bean上面起作用。对于没有在spring容器中注册的bean，它并不能执行任何操作。

`<context:component-scan>`除了具有
`<context:annotation-config />`的功能之外，还具有自动将带有@component,@service,@Repository等注解的对象注册到spring容器中的功能。

`<context:annotation-config />`仅仅查找定义在同一上下文中的bean的注解。这就意味着，如果你为DispatcherServlet将
`<context:annotation-config />`放置在WebApplicationContext中，那么它仅仅检查控制器中的@Autowired bean，而不是你的服务层bean。

@Autowired和@Inject的报错信息完全相同，他们都是通过 AutowiredAnnotationBeanPostProcessor类实现的依赖注入，二者具有可互换性。 @Resource通过CommonAnnotationBeanPostProcessor类实现依赖注入@Autowired，@Inject，@Resource和@Value注解是由Spring的BeanPostProcessor实现类来控制的，反过来告诉你你不能在BeanPostProcessor或BeanFactoryPostProcessor类型（任意之一）应用这些注解。这些类型必须明确地通过XML或使用Spring的@Bean方法来装配。

| Spring | javax.inject.* | javax.inject restrictions / comments |
|---|---|---|
| @Autowired | @Inject | @Inject 没有 'required'属性 |
| @Component | @Named |  |
| @Scope("singleton") | @Singleton | JSR-330的默认范围是Spring’s prototype。但是，为了保持与Spring的一般默认一致的，在Spring容器中声明的JSR-330 bean是一个singleton。为了使用范围上比单等，应使用Spring的@Scope注解。javax.inject还提供了@Scope注解。尽管如此，这个是用于代替创建自己的注解。 |
| @Qualifier | @Named |  |
| @Value |  |  |
| @Required |  |  |
| @Lazy |  |  |

#### component-scan
注解注入会在XML注入之前执行，因此通过两种方式，那么后面的配置会覆盖前面装配的属性。

@Component，@Service和@Controller。@Component是对Spring任意管理组件的通用刻板。@Repository，@Service和@Controller是对更多的特定用例@Component的专业化。
默认情况下，使用@Component，@Repository，@Service，@Controller注解或使用了进行自定义的@Component注解的类本身仅仅检测候选组件。
你可以修改并扩展这种行为，仅仅应用自定义的过滤器就可以了。在component-scan元素中添加include-filter或exclude-filter子元素就可以了。
每个过滤器元素需要type和expression属性。

|  |  |  |
|---|---|---|
| annotation（注解）| org.example.SomeAnnotation | 在目标组件的类型层表示的注解 |
| assignable（注解）| org.example.SomeClass | 目标组件分配去（扩展/实现）的类（接口） |
| aspectj| org.example..*Service+  | AspectJ类型表达式来匹配目标组件 |
| regex（正则表达式）| org\.example\.Default.* | 正则表达式来匹配目标组件类的名称 |
| custom（自定义）| org.example.MyTypeFilter | 自定义 |