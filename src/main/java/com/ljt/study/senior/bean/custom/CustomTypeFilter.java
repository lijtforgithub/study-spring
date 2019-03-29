package com.ljt.study.senior.bean.custom;

import java.io.IOException;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import com.ljt.study.senior.bean.entity.Dog;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午4:13:14
 */
public class CustomTypeFilter implements TypeFilter {

	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		return Dog.class.getName().equals(metadataReader.getClassMetadata().getClassName());
	}

}
