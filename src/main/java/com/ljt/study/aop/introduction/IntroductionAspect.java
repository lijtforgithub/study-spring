package com.ljt.study.aop.introduction;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * 为目标类中再增加一个目标方法
 *
 * @author LiJingTang
 * @version 2015年9月16日下午4:58:01
 */
@Aspect
class IntroductionAspect {

    @DeclareParents(value = "com.ljt.study.aop.service.*+", defaultImpl = IntroductionServiceImpl.class)
    public static IntroductionService introductionService;

}
