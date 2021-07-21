package com.ljt.study.pp.param;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author LiJingTang
 * @date 2021-07-20 13:59
 */
@Slf4j
class ParameterNameDiscovererTest {

    @Test
    void def() {
        ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        Method m1 = ReflectionUtils.findMethod(ParamTest.class, "m1", String.class, int.class);
        assert m1 != null;
        String[] parameterNames = discoverer.getParameterNames(m1);
        log.info(Arrays.toString(parameterNames));
    }
}

class ParamTest {

    void m1(String content, int num) {

    }

}

