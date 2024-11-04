## 知识点

#### 加载Bean

1. @Bean
2. @ComponentScan 配合@Component、@Controller、@Service、@Repository
> @ComponentScan: If specific packages are not defined, scanning will occur from the package of the class that declares this annotation.
> the {`<context:component-scan\>`} element has an {annotation-config} attribute; however, this annotation does not.  
> This is because in almost all cases when using {@ComponentScan}, default annotation config processing (e.g. processing {@Autowired} and friends) is assumed.
3. @Import
    1. @Import + ImportSelector(延迟 DeferredImportSelector)
    2. @Import + ImportBeanDefinitionRegistrar
4. FactoryBean（懒加载 而且没有生命周期那套逻辑 SmartFactoryBean）
5. InstanceSupplier
6. factory-method 和 factory-bean
#### Scope
懒加载和原型是在getBean的时候实例化对象。容器启动完成不会初始化。
#### LifeCycle
1. 实例化
2. populateBean(@Autowired/prop)
3. initializeBean
    1. invokeAwareMethods(BeanNameAware/BeanClassLoaderAware/BeanFactoryAware)
    2. BeanPostProcessor.postProcessBeforeInitialization(@PostConstruct/ApplicationContextAwareProcessor)
    3. invokeInitMethods(InitializingBean.afterPropertiesSet/initMethod)
    4. BeanPostProcessor.postProcessAfterInitialization
4. @PreDestroy
5. DisposableBean.destroy
6. destroyMethod
#### EmbeddedValueResolverAware
#### AopContext.currentProxy()

#### 循环依赖

- **构造参数循环依赖**

通过构造器注入构成的循环依赖，此依赖是无法解决的，只能抛出BeanCurrentlyIn CreationException异常表示循环依赖。

- **setter方式单例** 实例化、初始化

  对于"prototype"作用域bean，Spring容器无法完成依赖注入，因为Spring容器不进行缓存"prototype"作用域的bean，因此无法提前暴露一个创建中的bean。

## 事务传播机制

#### 父事务的传播机制为REQUIRED的前提

- 子事务的传播机制为REQUIRED

| 子事务  | 主事务             | 结论   |
| ---- | --------------- | ---- |
| 异常   | 正常 并try-catch异常 | 均回滚  |
| 正常   | 异常              | 均回滚  |
| 正常   | 异常 并try-catch异常 | 不回滚  |

- 子事务的传播机制为REQUIRES_NEW

| 子事务  | 主事务             | 结论       |
| ---- |-----------------| -------- |
| 异常   | 正常 并try-catch异常 | 子回滚 主不回滚 |
| 正常   | 异常              | 子不回滚 主回滚 |
| 异常   | 正常(不try相当于异常)   | 均回滚      |

- 子事务的传播机制为NESTED  
  在嵌套事务中，可以在父事务和子事务之间进行嵌套调用，每个子事务都是父事务的一部分。当嵌套事务中的任何一个事务提交或回滚时，它们所处的上下文都会被更新。当父事务提交时，所有子事务都会一起提交，只有当父事务回滚时，所有子事务才会回滚。
  嵌套事务只在外部事务存在时才生效。如果没有外部事务存在，则嵌套事务会像普通的事务一样进行提交和回滚。

| 子事务  | 主事务             | 结论       |
| ---- | --------------- | -------- |
| 异常   | 正常 并try-catch异常 | 子回滚 主不回滚 |
| 正常   | 异常              | 均回滚      |
| 异常   | 正常(不try相当于异常)  | 均回滚      |

