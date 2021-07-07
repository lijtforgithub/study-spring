package com.ljt.study.code.lifecycle;

import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * @author LiJingTang
 * @date 2021-07-06 16:09
 */
public class CustomSmartInitializingSingleton implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {

    }

}
