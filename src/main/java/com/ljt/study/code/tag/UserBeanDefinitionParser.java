package com.ljt.study.code.tag;

import com.ljt.study.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * @author LiJingTang
 * @date 2021-03-14 10:41
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private static final String PWD_ATTRIBUTE = "password";

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);

        String id = StringUtils.trimToNull(element.getAttribute(ID_ATTRIBUTE));
        String name = StringUtils.trimToNull(element.getAttribute(NAME_ATTRIBUTE));
        String password = StringUtils.trimToNull(element.getAttribute(PWD_ATTRIBUTE));

        if (Objects.nonNull(id)) {
            builder.addPropertyValue(ID_ATTRIBUTE, id);
        }
        if (Objects.nonNull(name)) {
            builder.addPropertyValue(NAME_ATTRIBUTE, name);
        }
        if (Objects.nonNull(password)) {
            builder.addPropertyValue(PWD_ATTRIBUTE, password);
        }
    }

}
