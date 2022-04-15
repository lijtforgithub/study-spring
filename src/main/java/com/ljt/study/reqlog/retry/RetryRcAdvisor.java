package com.ljt.study.reqlog.retry;

import com.ljt.study.reqlog.RequestLogHolder;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author jtli3
 * @date 2022-02-23 10:37
 */
@Slf4j
public class RetryRcAdvisor extends StaticMethodMatcherPointcutAdvisor {

    private static final long serialVersionUID = -1210382191888075322L;

    private static final Class<? extends Annotation>[] ANNOTATION_CLASSES = new Class[]{RetryRC.class};

    public RetryRcAdvisor() {
        setAdvice(new RetryRcInterceptor());
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Method m = method;

        if (isAnnotationPresent(m)) {
            return true;
        }

        try {
            m = targetClass.getMethod(m.getName(), m.getParameterTypes());
            return isAnnotationPresent(m) ;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private boolean isAnnotationPresent(Method method) {
        return Arrays.stream(ANNOTATION_CLASSES).anyMatch(c -> Objects.nonNull(AnnotationUtils.findAnnotation(method, c)));
    }

}

@Slf4j
class RetryRcInterceptor implements MethodInterceptor {

    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation mi) throws Throwable {
        RetryRC retry = AnnotationUtils.getAnnotation(mi.getMethod(), RetryRC.class);

        if (Objects.nonNull(retry)) {
            RequestLogHolder.get().setRetry(retry.value()).setRetryDesc(retry.desc());
        }

        try {
            return mi.proceed();
        } finally {
            RequestLogHolder.get().setRetry(null).setRetryDesc(null);
        }
    }

}
