package com.ljt.study.pp.spi;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author LiJingTang
 * @date 2021-07-19 17:16
 */
class SpiTest {

    @Test
    void spi() {
        List<SpringSpiService> services = SpringFactoriesLoader.loadFactories(SpringSpiService.class, null);
        services.forEach(SpringSpiService::hello);
    }

}
