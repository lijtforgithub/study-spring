package com.ljt.study.code.proxy.advisor.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * @author jtli3
 * @date 2022-03-31 14:36
 */
@Slf4j
class AnnotationTest {

    public static void main(String[] args) throws NoSuchMethodException {
        Class<CustomService> clazz = CustomService.class;
        Method methodA = clazz.getMethod("methodA");
        Log annotation = AnnotationUtils.findAnnotation(methodA, Log.class);
        log.info("{} {} => {}", CustomService.class.getSimpleName(), methodA.getName(), annotation);

        Method methodB = clazz.getMethod("methodB");
        annotation = AnnotationUtils.findAnnotation(methodB, Log.class);
        log.info("{} {} => {}", CustomService.class.getSimpleName(), methodB.getName(), annotation);

        Class<CustomServiceImpl> clazz2 = CustomServiceImpl.class;
        methodA = clazz2.getMethod("methodA");
        annotation = AnnotationUtils.findAnnotation(methodA, Log.class);
        log.info("{} {} => {}", CustomServiceImpl.class.getSimpleName(), methodA.getName(), annotation);

        methodB = clazz2.getMethod("methodB");
        annotation = AnnotationUtils.findAnnotation(methodB, Log.class);
        log.info("{} {} => {}", CustomServiceImpl.class.getSimpleName(), methodB.getName(), annotation);
    }

}
