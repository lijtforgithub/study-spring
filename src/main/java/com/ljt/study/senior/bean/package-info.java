package com.ljt.study.senior.bean;

/**
	加载Bean的方式：
	1.@Bean
	2.@ComponentScan 配合@Component、@Controller、@Service、@Repository.
  	  @ComponentScan: If specific packages are not defined, scanning will occur from the package of the class that declares this annotation.
  		the {<context:component-scan>} element has an {annotation-config} attribute; however, this annotation does not. 
  		This is because in almost all cases when using {@ComponentScan}, default annotation config processing (e.g. processing {@Autowired} and friends) is assumed.
	3.@Import
	4.@Import + ImportSelector
	5.@Import + ImportBeanDefinitionRegistrar
 */
