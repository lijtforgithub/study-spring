package com.ljt.study.senior.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * @author LiJingTang
 * @date 2019年5月8日 下午2:16:24
 */
@Configuration
@PropertySource("classpath:senior/config.properties")
public class ValueConfig {
    
    @Value("normal")
    private String normal; // 注入普通字符串

    @Value("#{systemProperties['os.name']}")
    private String systemPropertiesName; // 注入操作系统属性

    @Value("#{ T(java.lang.Math).random() * 100.0 }")
    private double randomNumber; //注入表达式结果
    
    @Value("${说明}")
    private String explanation; // 注入资源文件属性
    
    @Value("classpath:senior/config.properties")
    private Resource resourceFile; // 注入文件资源

    @Value("http://www.baidu.com")
    private Resource testUrl; // 注入URL资源
    
    @Value("#{beanInject.another}")
    private String fromAnotherBean; // 注入其他Bean属性：注入beanInject对象的属性another，类具体定义见下面
    
    public String getNormal() {
        return normal;
    }
    public String getSystemPropertiesName() {
        return systemPropertiesName;
    }
    public double getRandomNumber() {
        return randomNumber;
    }
    public String getExplanation() {
        return explanation;
    }
    public Resource getResourceFile() {
        return resourceFile;
    }
    public Resource getTestUrl() {
        return testUrl;
    }
    public String getFromAnotherBean() {
        return fromAnotherBean;
    }

    @Bean
    public BeanInject beanInject() {
        return new BeanInject();
    }
}

class BeanInject {
    
    @Value("其他Bean的属性")
    private String another;

    public String getAnother() {
        return another;
    }
    public void setAnother(String another) {
        this.another = another;
    }
}
