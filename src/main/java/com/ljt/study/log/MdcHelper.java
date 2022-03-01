package com.ljt.study.log;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.Objects;
import java.util.Set;

/**
 * @author LiJingTang
 * @date 2022-02-26 16:13
 */
class MdcHelper {
    
    private MdcHelper() {}

    private static final String PREFIX = "_pol_";
    private static final String LOG_TYPE = PREFIX + "log_type";
    private static final String BUSINESS_TYPE = PREFIX + "business_type";
    private static final String CLIENT_IP = PREFIX + "client_ip";
    private static final String CLIENT_MAC = PREFIX + "client_mac";
    private static final String TIMESTAMPE = PREFIX + "timestampe";
    private static final String USER_ID = PREFIX + "user_id";
    private static final String ORG_ID = PREFIX + "org_id";
    private static final String EMPI = PREFIX + "empi";
    private static final String IMA_HEADER = PREFIX + "ima_header";
    private static final String CHAIN_ID = PREFIX + "chain_id";
    private static final String CLASS_METHOD = PREFIX + "class_method";
    private static final String TIME_COST = PREFIX + "time_cost";
    private static final String REQ_URL = PREFIX + "req_url";
    private static final String REQ_METHOD = PREFIX + "req_method";
    private static final String REQ_USER_AGENT = PREFIX + "req_user_agent";

    static void putPointLog(PointLog pointLog) {
        if (Objects.nonNull(pointLog)) {
            MDC.put(LOG_TYPE, String.valueOf(ObjectUtils.defaultIfNull(pointLog.getLogType(), LogConstant.LOG_TYPE)));
            MDC.put(BUSINESS_TYPE, StringUtils.trimToEmpty(pointLog.getBusinessType()));
            MDC.put(CLIENT_IP, StringUtils.trimToEmpty(pointLog.getClientIp()));
            MDC.put(CLIENT_MAC, StringUtils.trimToEmpty(pointLog.getClientMac()));
            MDC.put(TIMESTAMPE, String.valueOf(ObjectUtils.defaultIfNull(pointLog.getTimestampe(), System.currentTimeMillis())));
            MDC.put(USER_ID, StringUtils.trimToEmpty(pointLog.getUserId()));
            MDC.put(ORG_ID, StringUtils.trimToEmpty(pointLog.getOrgId()));
            MDC.put(EMPI, StringUtils.trimToEmpty(pointLog.getEmpi()));
            MDC.put(IMA_HEADER, StringUtils.trimToEmpty(pointLog.getImaHeader()));
            MDC.put(CHAIN_ID, StringUtils.trimToEmpty(pointLog.getChainId()));
            MDC.put(CLASS_METHOD, StringUtils.trimToEmpty(pointLog.getClassMethod()));
            MDC.put(REQ_URL, StringUtils.trimToEmpty(pointLog.getReqUrl()));
            MDC.put(REQ_METHOD, StringUtils.trimToEmpty(pointLog.getReqMethod()));
            MDC.put(REQ_USER_AGENT, StringUtils.trimToEmpty(pointLog.getReqUserAgent()));
            MDC.put(TIME_COST, String.valueOf(ObjectUtils.defaultIfNull(pointLog.getTimeCost(), 0)));
        }
    }

    static void setBusinessType(String businessType) {
        MDC.put(BUSINESS_TYPE, StringUtils.trimToEmpty(businessType));
    }

    static String getBusinessType() {
        return MDC.get(BUSINESS_TYPE);
    }

    static void setTimeCost(Long timeCost) {
        MDC.put(TIME_COST, String.valueOf(ObjectUtils.defaultIfNull(timeCost, 0)));
    }
    
    static void clearPointLog() {
        Set<String> keys = MDC.getCopyOfContextMap().keySet();
        keys.forEach(k -> {
            if (k.startsWith(PREFIX)) {
                MDC.remove(k);
            }
        });
    }

}
