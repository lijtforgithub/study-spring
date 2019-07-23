package com.ljt.study.senior.bean.custom;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import com.ljt.study.senior.bean.entity.Animal;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午3:45:08
 */
@Slf4j
public class CustomImportSelector implements ImportSelector {
	
	/**
	 * @param importingClassMetadata 当前标注@Import注解类的所有注解信息
	 * 
	 * @return 返回要导入类的全类名
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		log.debug(importingClassMetadata.getAnnotationTypes().toString());
		
		return new String[] {Animal.class.getName()};
	}

}
