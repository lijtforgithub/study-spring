package com.ljt.study.ioc.bean.xml.methodinjection;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 容器仅仅创建单例bean A一次，因此仅仅有一次机会去设置属性。容器不能每次为bean A提供所需的bean B新的实例。
 * 解决方法就是放弃一些控制反转。你可通过实现ApplicationContextAware接口以让bean A去感知容器，
 * 而且在每次bean A需要bean B的实例时，可以让容器调用getBean("B")去获取（一个新的）bean B。
 * 
 * 暂时不知道怎么用
 * @author LiJingTang
 * @version 2015年8月23日 下午9:41:50
 */
public class CommandManager implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Object process(Map<String, Object> commandState) {
        Command command = createCommand(); // 抓取相应命令的新实例
        command.setState(commandState); // 在命令实例上（希望标记新的）设置状态
        
        return command.execute();
    }

    protected Command createCommand() {
        return this.applicationContext.getBean("command", Command.class);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
