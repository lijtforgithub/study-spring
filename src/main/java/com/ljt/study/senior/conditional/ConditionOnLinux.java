package com.ljt.study.senior.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午11:17:08
 */
public class ConditionOnLinux implements Condition {

	private static final String WINDOWS = "Linux";
	private static final String OS_NAME = "os.name";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return context.getEnvironment().getRequiredProperty(OS_NAME).contains(WINDOWS);
	}

}
