package com.ljt.study.annotation.bean.custom;

import java.io.IOException;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import com.ljt.study.annotation.bean.entity.Dog;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午4:13:14
 */
public class CustomTypeFilter implements TypeFilter {

	/**
	 * @param metadataReader 读取到的当前正在扫描的类的信息
	 * @param metadataReaderFactory 可以获取到其他任何类的信息
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
		return Dog.class.getName().equals(metadataReader.getClassMetadata().getClassName());
	}

}
