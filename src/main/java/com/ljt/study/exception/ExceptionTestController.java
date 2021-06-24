package com.ljt.study.exception;

import com.ljt.study.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LiJingTang
 * @date 2020-01-04 20:08
 */
@Controller("exceptionTestController")
@RequestMapping("/exception")
public class ExceptionTestController {

    @RequestMapping("/null")
    public void testNullPointerException() {
        User user = null;
        // 这里就会发生空指针异常，然后就会返回定义在SpringMVC配置文件中的null视图
        System.out.println(user.getId());
    }

    @RequestMapping("/number")
    public void testNumberFormatException() {
        // 这里就会发生NumberFormatException，然后就会返回定义在SpringMVC配置文件中的number视图
        Integer.parseInt("abc");
    }

    @RequestMapping("/default")
    public void testDefaultException() {
        // 由于该异常类型在SpringMVC的配置文件中没有指定，所以就会返回默认的exception视图
        throw new RuntimeException("Error!");
    }

}
