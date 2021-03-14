package com.ljt.study.pp.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:09
 */
public class ConditionOnWindows implements Condition {

    private static final String OS = "Windows";
    private static final String OS_NAME = "os.name";

    /**
     * @param context  判断条件能使用的上下文环境
     * @param metadata 注解信息
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getRequiredProperty(OS_NAME).contains(OS);
    }

}
