package com.ljt.study.reqlog.enums;

import com.ljt.study.reqlog.retry.RetryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.ljt.study.reqlog.enums.SystemEnum.ICDSS;

/**
 * @author jtli3
 * @date 2022-01-04 11:20
 */
@Getter
@AllArgsConstructor
public enum ApiPathEnum {

    /**
     * 请求
     */
    GET_TOKEN("/auth/oauth/token", "获取Token", ICDSS, null);

    /**
     * 相对路径
     */
    private final String path;
    /**
     * 接口描述
     */
    private final String desc;


    /**
     * 系统平台
     */
    private final SystemEnum system;
    /**
     * 接口失败是否需要重试
     */
    private final RetryEnum retry;

}
