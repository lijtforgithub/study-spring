package com.ljt.study.code.editor;

import com.ljt.study.entity.Address;

import java.beans.PropertyEditorSupport;

/**
 * 自定义属性解析器
 *
 * @author LiJingTang
 * @date 2021-03-14 11:28
 */
public class AddressEditor extends PropertyEditorSupport {

    private static final String SEP = "_";

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] array = text.split(SEP);

        Address address = new Address();
        address.setProvince(array[0]);
        address.setCity(array[1]);
        address.setRegion(array[2]);
        setValue(address);
    }

}
