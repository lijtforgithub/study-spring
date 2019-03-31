package com.ljt.study.senior.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午11:16:19
 */
public class ConditionOnWindows implements Condition {

	private static final String WINDOWS = "Windows";
	private static final String OS_NAME = "os.name";

	/**
	 * @param context 判断条件能使用的上下文环境
	 * @param metadata 注解信息
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return context.getEnvironment().getRequiredProperty(OS_NAME).contains(WINDOWS);
	}

}
