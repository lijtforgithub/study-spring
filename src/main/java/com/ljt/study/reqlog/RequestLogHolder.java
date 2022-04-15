package com.ljt.study.reqlog;

import java.util.ArrayList;
/**
 * @author jtli3
 * @date 2022-01-06 18:22
 */
public abstract class RequestLogHolder {

    private RequestLogHolder() {}

    public static final ThreadLocal<RequestLog> LOG_THREAD_LOCAL = new InheritableThreadLocal<RequestLog>() {
        @Override
        protected RequestLog initialValue() {
            RequestLog log = new RequestLog();
            log.setStartDateTime(System.currentTimeMillis());
            log.setItemList(new ArrayList<>());
            log.setChainError(new ArrayList<>());
            return log;
        }
    };

    public static RequestLog get() {
        return LOG_THREAD_LOCAL.get();
    }

    public static void set(RequestLog requestLog) {
        LOG_THREAD_LOCAL.set(requestLog);
    }

    public static void remove() {
        LOG_THREAD_LOCAL.remove();
    }

}
