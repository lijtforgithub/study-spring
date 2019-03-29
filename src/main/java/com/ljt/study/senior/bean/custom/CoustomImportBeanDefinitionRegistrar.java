package com.ljt.study.senior.bean.custom;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.ljt.study.entity.User;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午5:47:50
 */
public class CoustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		registry.registerBeanDefinition("registry_user", new RootBeanDefinition(User.class));
	}

}
