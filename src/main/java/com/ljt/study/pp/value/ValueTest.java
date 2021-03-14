package com.ljt.study.pp.value;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author LiJingTang
 * @date 2020-01-03 21:29
 */
@SpringJUnitConfig(ValueConfig.class)
public class ValueTest {

    @Autowired
    private ValueConfig config;

    @Test
    public void test() throws IOException {
        System.out.println(config);

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
