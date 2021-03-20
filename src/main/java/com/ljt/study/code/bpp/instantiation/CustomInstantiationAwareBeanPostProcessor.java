package com.ljt.study.code.bpp.instantiation;

import com.ljt.study.code.bpp.Bpp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author LiJingTang
 * @date 2021-03-19 10:10
 */
@Slf4j
public class CustomInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    /**
     * 实例化之前
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        log.info("{} 实例化之前", beanName);

        if (beanClass == Bpp.class) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                log.info("{} 方法执行之前", method.getName());

                Object retVal = methodProxy.invokeSuper(o, objects);

                log.info("{} 方法执行之后", method.getName());
                return retVal;
            });
            return enhancer.create();
        }

        return null;
    }

    /**
     * 实例化之后
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        boolean flag = System.currentTimeMillis() % 2 == 0;
        log.info("{} 实例化之后，是否执行【{}】 postProcessProperties", beanName, flag);
        return flag;
    }

    /**
     * 属性处理
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        log.info("{} postProcessProperties {}", beanName, pvs.toString());
        return null;
    }

    /**
     * 初始化之前
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("{} 初始化之前", beanName);
        return bean;
    }

    /**
     * 初始化之后
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("{} 初始化之后", beanName);
        return bean;
    }

}
