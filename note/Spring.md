#### 加载Bean
1. @Bean
2. @ComponentScan 配合@Component、@Controller、@Service、@Repository
> @ComponentScan: If specific packages are not defined, scanning will occur from the package of the class that declares this annotation.
the {`<context:component-scan\>`} element has an {annotation-config} attribute; however, this annotation does not.  
This is because in almost all cases when using {@ComponentScan}, default annotation config processing (e.g. processing {@Autowired} and friends) is assumed.
3. @Import
    1. @Import + ImportSelector
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