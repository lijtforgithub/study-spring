package com.ljt.study.reqlog.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author jtli3
 * @date 2022-02-25 17:06
 */
@Getter
@AllArgsConstructor
public enum BusinessTypeEnm {

    /**
     * 埋点日志业务类型
     */
    COMMON("1000", "通用日志埋点"),
    AI("2000", "调用链");


    private final String code;
    private final String desc;

}
