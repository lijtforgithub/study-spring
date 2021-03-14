package com.ljt.study.code.editor;

import com.ljt.study.entity.Address;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 注册自定义属性解析器
 *
 * @author LiJingTang
 * @date 2021-03-14 11:35
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(Address.class, new AddressEditor());
    }

}
