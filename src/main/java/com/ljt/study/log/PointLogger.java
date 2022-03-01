package com.ljt.study.log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 埋点日志输出类
 *
 * @author jtli3
 * @date 2022-02-25 16:38
 */
public class PointLogger {

    private static final Logger LOG = LoggerFactory.getLogger("ima.log.point");

    private PointLogger() {}

    public static void info(String format, Object... arguments) {
        LOG.info(format, arguments);
    }

    public static void info(BusinessTypeEnm typeEnm, String format, Object... arguments) {
        if (Objects.nonNull(typeEnm)) {
            String origin = MdcHelper.getBusinessType();
            MdcHelper.setBusinessType(typeEnm.getCode());

            info(format, arguments);

            MdcHelper.setBusinessType(StringUtils.trimToEmpty(origin));
        } else {
            info(format, arguments);
        }
    }

}
