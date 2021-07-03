package com.ljt.study.aop.introduction;

/**
 * @author LiJingTang
 * @version 2015年9月17日下午12:44:30
 */
class IntroductionServiceImpl implements IntroductionService {

    @Override
    public void introduction() {
        System.out.println("为目标类中增加一个方法的默认实现");
    }

}
