package com.ljt.study.sse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jtli3
 * @date 2022-02-25 16:38
 */
public class PointLogger {

    private static final Logger LOG = LoggerFactory.getLogger("ima.log.point");

    private PointLogger() {}

    public static void debug(String format, Object... arguments) {
        LOG.debug(format, arguments);
    }

    public static void info(String format, Object... arguments) {
        LOG.info(format, arguments);
    }

    public static void warn(String format, Object... arguments) {
        LOG.warn(format, arguments);
    }

    public static void error(String format, Object... arguments) {
        LOG.error(format, arguments);
    }

}
