package com.ljt.study.reqlog.api;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author jtli3
 * @date 2022-01-20 11:08
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class MedicalAiException extends Exception {

    private static final long serialVersionUID = -540044898587173082L;

    /**
     * @see MedicalAiExceptionEnum
     * 异常code
     */
    private final Integer code;
    /**
     * 内部调用异常时具体异常信息
     */
    private List<ChainItem> chainError;
    /**
     * 内部调用异常信息方法返回值
     */
    private String data;


    public MedicalAiException(int code, String message) {
        super(message);
        this.code = code;
    }


    @Data
    @AllArgsConstructor
    public static class ChainItem {

        /**
         * 内部请求地址
         */
        private String path;
        /**
         * 内部异常码
         */
        private Integer code;
        /**
         * 内部异常信息
         */
        private String message;
    }


    public <T> T defaultHandle (Class<T> clazz) {
        if (MedicalAiExceptionEnum.CHAIN.getCode() == code) {
            log.error("调用AI平台异常: {}", JSON.toJSONString(chainError));

            if (StringUtils.isBlank(data)) {
                return null;
            }

            if (clazz == String.class) {
                return (T) data;
            } else {
                return JSON.parseObject(data, clazz);
            }
        } else {
            log.error("调用AI平台异常", this);
            return null;
        }
    }

}
