package com.ljt.study.reqlog.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * @author jtli3
 * @date 2022-01-20 19:41
 */
@Data
@ConfigurationProperties(MedicalAiProperties.PREFIX)
public class MedicalAiProperties {

    static final String PREFIX = "ima.medical.ai";

    /**
     * 服务名称
     */
    private String serviceName = "ima-medical-ai";
    private int connectTimeout;
    private int readTimeout;

}
