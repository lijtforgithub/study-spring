package com.ljt.study.code.cycle;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2021-03-22 17:40
 */
@Slf4j
@Data
public class A  {

    private B b;

    public void print() {
        log.info(super.getClass().toString());
        log.info(this.getClass().toString());
    }

}
