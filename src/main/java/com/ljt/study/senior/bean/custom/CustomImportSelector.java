package com.ljt.study.senior.bean.custom;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.ljt.study.senior.bean.entity.Animal;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午3:45:08
 */
public class CustomImportSelector implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] {Animal.class.getName()};
	}

}
