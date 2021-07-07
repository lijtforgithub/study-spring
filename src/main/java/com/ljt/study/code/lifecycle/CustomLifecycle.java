package com.ljt.study.code.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * @author LiJingTang
 * @date 2021-07-06 16:10
 */
public class CustomLifecycle implements Lifecycle {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
