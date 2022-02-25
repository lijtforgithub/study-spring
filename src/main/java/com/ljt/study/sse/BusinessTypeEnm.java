package com.ljt.study.sse;

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
    COMMON("1000", "通用日志埋点");


    private final String code;
    private final String desc;

}
