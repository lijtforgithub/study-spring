package com.ljt.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author LiJingTang
 * @date 2020-01-03 20:47
 */
@Slf4j
public abstract class AbstractTest {

    private static final String SPRING_BEAN = "org.springframework";
    private static final String PCK_PREFIX = "com.ljt.study";

    protected ApplicationContext applicationContext;

    protected void printBeanDefinition() {
        Objects.requireNonNull(applicationContext, "容器为空");
        log.info("----------- 容器中自定义类的Bean定义名称（只是bean定义 并不是实例化的bean）");

        Stream.of(applicationContext.getBeanDefinitionNames())
                .filter(name -> !name.startsWith(SPRING_BEAN))
                .forEach(log::info);

        log.info("----------- 容器中自定义类的Bean定义名称");
    }

    private static final String SUFFIX = ".xml";

    protected void setApplicationContext(String fileName) {
        String pckSuffix = this.getClass().getPackage().getName().substring(PCK_PREFIX.length());
        String configLocations = pckSuffix.replaceAll("\\.", "/") + "/" + fileName + SUFFIX;
        applicationContext = new ClassPathXmlApplicationContext(configLocations);

        log.debug("----------- 容器启动完成【{}】", configLocations);
    }
    protected void setApplicationContext(Class<?> clazz) {
        applicationContext = new AnnotationConfigApplicationContext(clazz);

        log.debug("----------- 容器启动完成【{}】", clazz.getSimpleName());
    }

}
