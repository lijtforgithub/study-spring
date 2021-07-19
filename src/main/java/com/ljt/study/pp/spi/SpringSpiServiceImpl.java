package com.ljt.study.pp.spi;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2021-07-19 17:15
 */
@Slf4j
public class SpringSpiServiceImpl implements SpringSpiService {

    @Override
    public void hello() {
        log.info("Hello Spring SPI");
    }

}
