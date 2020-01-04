package com.ljt.study.ioc.bean.xml.methodinjection;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:00
 */
public  abstract class AbstractCommandManager {

    public Object process(Object commandState) {
        Command command = createCommand(); // // 抓取一个心得合适的Command接口的实例
        command.setState(commandState); // //在命令实例上（希望标记新的）设置状态

        return command.execute();
    }

    protected abstract Command createCommand();
}
