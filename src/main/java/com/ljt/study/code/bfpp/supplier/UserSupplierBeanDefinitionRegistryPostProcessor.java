package com.ljt.study.code.bfpp.supplier;

import com.ljt.study.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author LiJingTang
 * @date 2021-03-19 11:52
 */
public class UserSupplierBeanDefinitionRegistryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition userBean = beanFactory.getBeanDefinition("user");
        RootBeanDefinition beanDefinition = (RootBeanDefinition) userBean;
        beanDefinition.setInstanceSupplier(this::createUser);
        beanDefinition.setBeanClass(User.class);
    }

    private User createUser() {
        User user = new User();
        user.setName("Supplier 方式创建Bean");
        return user;
    }

}
