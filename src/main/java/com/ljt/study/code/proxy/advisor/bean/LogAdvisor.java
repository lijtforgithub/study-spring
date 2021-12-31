package com.ljt.study.code.proxy.advisor.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author LiJingTang
 * @date 2021-06-26 09:51
 */
@Slf4j
@Component
class LogAdvisor extends StaticMethodMatcherPointcutAdvisor {

    private static final long serialVersionUID = -1210382191888075322L;

    private static final Class<? extends Annotation>[] ANNOTATION_CLASSES = new Class[]{Log.class};

    public LogAdvisor() {
        setAdvice(new LogInterceptor());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        log.info("------------ {}, {}, {}", targetClass, isAnnotationPresent(targetClass), isAnnotationPresent(method));
        Method m = method;

        if (isAnnotationPresent(m)) {
            return true;
        }

        //The 'method' parameter could be from an interface that doesn't have the annotation.
        //Check to see if the implementation has it.
        if (Objects.nonNull(targetClass)) {
            try {
                m = targetClass.getMethod(m.getName(), m.getParameterTypes());
                return isAnnotationPresent(m) || isAnnotationPresent(targetClass);
            } catch (NoSuchMethodException ignored) {
                //default return value is false.  If we can't find the method, then obviously
                //there is no annotation, so just use the default return value.
            }
        }

        return false;
    }

    private boolean isAnnotationPresent(Class<?> targetClazz) {
        return Arrays.stream(ANNOTATION_CLASSES).anyMatch(c -> Objects.nonNull(AnnotationUtils.findAnnotation(targetClazz, c)));
    }

    private boolean isAnnotationPresent(Method method) {
        return Arrays.stream(ANNOTATION_CLASSES).anyMatch(c -> Objects.nonNull(AnnotationUtils.findAnnotation(method, c)));
    }

}
