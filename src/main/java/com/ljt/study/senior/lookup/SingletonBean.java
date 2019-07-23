package com.ljt.study.senior.lookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2019年7月23日 上午10:44:58
 */
@Slf4j
@Component
public abstract class SingletonBean {
    
    // 第一种解决办法    
//    @Autowired
//    private ApplicationContext applicationContext;
//    
//    public void print() {
//        PrototypeBean bean = applicationContext.getBean(PrototypeBean.class);
//        log.info("PrototypeBean hashCode = {}", bean.hashCode());
//        bean.say();
//    }

    // 第二种解决办法
    
    public void print() {
      PrototypeBean bean = getPrototypeBean();
      log.info("PrototypeBean hashCode = {}", bean.hashCode());
      bean.say();
    }
    
    @Lookup
    protected abstract PrototypeBean getPrototypeBean();
    
}
