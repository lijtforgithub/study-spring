package com.ljt.study.senior.value;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.ljt.study.AbstractTest;

/**
 * @author LiJingTang
 * @date 2019年5月8日 下午2:16:53
 */
public class ValueTest extends AbstractTest {
    
    @Test
    public void test() throws IOException {
        applicationContext = new AnnotationConfigApplicationContext(ValueConfig.class);
        ValueConfig config = applicationContext.getBean(ValueConfig.class);
        
        System.out.println(config.getNormal());
        System.out.println(config.getSystemPropertiesName());
        System.out.println(config.getRandomNumber());
        
        System.out.println(config.getExplanation());
        System.out.println(config.getResourceFile());
        System.out.println(FileUtils.readFileToString(config.getResourceFile().getFile(), StandardCharsets.UTF_8));
        System.out.println(config.getTestUrl());
        
        System.out.println(config.getFromAnotherBean());
    }

}
