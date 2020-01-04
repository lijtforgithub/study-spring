package com.ljt.study.pp.bean;

import com.ljt.study.entity.User;
import com.ljt.study.pp.bean.entity.Animal;
import com.ljt.study.pp.bean.entity.Dog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author LiJingTang
 * @Import
 * @date 2020-01-03 21:11
 */
@Configuration
@Import({Dog.class, CustomImportSelector.class, CustomImportBeanDefinitionRegistrar.class})
public class ImportConfig {
}

@Slf4j
class CustomImportSelector implements ImportSelector {

    /**
     * @param importingClassMetadata 当前标注@Import注解类的所有注解信息
     * @return 返回要导入类的全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        log.debug(importingClassMetadata.getAnnotationTypes().toString());

        return new String[]{Animal.class.getName()};
    }
}

class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata 当前标注@Import注解类的所有注解信息
     * @param registry               BeanDefinition 注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 指定bean名
        registry.registerBeanDefinition("import_registry_user", new RootBeanDefinition(User.class));
    }
}
