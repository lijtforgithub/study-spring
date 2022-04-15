package com.ljt.study.reqlog.retry;

/**
 * 重试类型
 *
 * @author jtli3
 * @date 2022-03-31 11:08
 */
public enum RetryEnum {

    /**
     * 只重试
     */
    ONLY_RETRY,
    /**
     * 重试失败且保存
     */
    RETRY_SAVE;

}