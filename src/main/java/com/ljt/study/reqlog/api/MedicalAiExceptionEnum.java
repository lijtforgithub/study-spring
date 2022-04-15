package com.ljt.study.reqlog.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jtli3
 * @date 2022-01-20 16:31
 */
@Getter
@AllArgsConstructor
public enum MedicalAiExceptionEnum {

    OK(200, "成功"),
    BAD_REQUEST(400, "请求参数异常"),
    SERVER_ERROR(500, "服务端异常"),
    CHAIN(510, "调用链方法内部异常");

    private final int code;
    private final String desc;

}
