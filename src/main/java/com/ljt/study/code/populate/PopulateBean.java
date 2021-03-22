package com.ljt.study.code.populate;

import com.ljt.study.entity.Address;
import com.ljt.study.entity.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * @author LiJingTang
 * @date 2021-03-20 18:53
 */
@Data
public class PopulateBean {

    @Value("${os.name}")
    private String osName;
    @Autowired
    private User user;

    /**
     * @Autowired 根据类型查找 如果多个 找个和属性名一样的bean 如果不能确定就抛异常
     * @Resource 的注解先被解析 如果设置属性之后 解析@Autowired的时候不会重新赋值
     */
    @Autowired
    @Resource(name = "address2")
    private Address address;

}
