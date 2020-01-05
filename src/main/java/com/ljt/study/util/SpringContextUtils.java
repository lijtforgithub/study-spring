package com.ljt.study.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;
import java.util.Map;

/**
 * @author LiJingTang
 * @date 2020-01-04 20:05
 */
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static String getProperty(String key) {
        return applicationContext.getEnvironment().getProperty(key);
    }

    public static String getRequiredProperty(String key) {
        return applicationContext.getEnvironment().getRequiredProperty(key);
    }

    public static String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return applicationContext.getMessage(code, args, defaultMessage, locale);
    }

    public static String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return applicationContext.getMessage(code, args, locale);
    }

    public static String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return applicationContext.getMessage(resolvable, locale);
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return applicationContext.getBeansOfType(type);
    }

}