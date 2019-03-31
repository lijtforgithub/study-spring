package com.ljt.study.senior.bean.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.ljt.study.senior.bean.entity.Animal;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午3:45:08
 */
public class CustomImportSelector implements ImportSelector {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomImportSelector.class);

	/**
	 * @param importingClassMetadata 当前标注@Import注解类的所有注解信息
	 * 
	 * @return 返回要导入类的全类名
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		LOG.debug(importingClassMetadata.getAnnotationTypes().toString());
		
		return new String[] {Animal.class.getName()};
	}

}
