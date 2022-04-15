package com.ljt.study.reqlog.enums;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ljt.study.reqlog.RequestLog;
import com.ljt.study.reqlog.RequestLogHolder;
import com.ljt.study.reqlog.api.MedicalAiException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * @author jtli3
 * @date 2022-01-04 11:20
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum SystemEnum {

    /**
     * 枚举项
     */
    MDM("mdm", "数据中心") {
        @Override
        public void handleLog(String resp, RequestLog.LogItem item) {
            if (SC_OK == item.getStatusCode()) {
                item.setResp(resp);
            } else {
                item.setErrorMsg(resp);
                MedicalAiException.ChainItem chainItem = new MedicalAiException.ChainItem(item.getPath(), item.getStatusCode(), resp);
                RequestLogHolder.get().getChainError().add(chainItem);
            }
        }

        private final Set<String> headerKeys = Sets.newHashSet();

        @Override
        public Map<String, String> getHeaders(HttpHeaders headers) {
            Map<String, String> map = Maps.newHashMap();
            if (Objects.nonNull(headers)) {
                headers.forEach((key, value) -> {
                    if (headerKeys.contains(key) && !CollectionUtils.isEmpty(value)) {
                        map.put(key, value.get(0));
                    }
                });
            }

            return map;
        }
    },
    ICDSS("icdss", "辅诊平台") {
        @Override
        public void handleLog(String resp, RequestLog.LogItem item) {
            JSONObject obj = JSON.parseObject(resp);
            int innerCode = obj.getIntValue("code");

            if (SC_OK == item.getStatusCode() && 0 == innerCode) {
                item.setResp(resp);
            } else {
                item.setStatusCode(innerCode);
                item.setErrorMsg(resp);

                MedicalAiException.ChainItem chainItem = new MedicalAiException.ChainItem(item.getPath(), innerCode, obj.getString("message"));
                RequestLogHolder.get().getChainError().add(chainItem);
            }
        }
    },

    UN_KNOW("un_known", "未知") {
        @Override
        public void handleLog(String resp, RequestLog.LogItem item) {
            log.warn("未知系统接口 {}", resp);
            if (SC_OK == item.getStatusCode()) {
                item.setResp(resp);
            } else {
                item.setErrorMsg(resp);
            }
        }
    };


    private final String code;
    private final String desc;

    public abstract void handleLog(String resp, RequestLog.LogItem item);

    public Map<String, String> getHeaders(HttpHeaders headers) {
        return null;
    }

}