package com.ljt.study.pp.bean;

import com.ljt.study.pp.bean.entity.Dog;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * @ComponentScan
 *
 * @author LiJingTang
 * @date 2020-01-03 21:08
 */
@Configuration
@ComponentScan(basePackages = "com.ljt.study.pp.bean.entity", useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = CustomTypeFilter.class))
//@ComponentScans
public class ComponentScanConfig {

}

class CustomTypeFilter implements TypeFilter {

    /**
     * @param metadataReader        读取到的当前正在扫描的类的信息
     * @param metadataReaderFactory 可以获取到其他任何类的信息
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) {
        return Dog.class.getName().equals(metadataReader.getClassMetadata().getClassName());
    }
}

