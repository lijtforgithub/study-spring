package com.ljt.study.pp.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:08
 */
public class ConditionOnLinux implements Condition {

    private static final String WINDOWS = "Linux";
    private static final String OS_NAME = "os.name";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getRequiredProperty(OS_NAME).contains(WINDOWS);
    }

}
