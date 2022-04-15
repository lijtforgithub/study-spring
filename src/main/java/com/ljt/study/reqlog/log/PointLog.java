package com.ljt.study.reqlog.log;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 日志埋点上下文
 *
 * @author jtli3
 * @date 2022-02-24 14:36
 */
@Data
@Accessors(chain = true)
public class PointLog implements Serializable {

    private static final long serialVersionUID = -7466670915523028565L;

    /**
     * 日志类型，暂时分为前端日志和后端日志
     */
    private Integer logType;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 客户端IP，需要考虑反向代理
     */
    private String clientIp;
    /**
     * 客户端mac地址
     */
    private String clientMac;
    /**
     * 调用时间戳
     */
    private Long timestampe;
    /**
     * 用户主键id
     */
    private String userId;
    /**
     * 机构主键id
     */
    private String orgId;
    /**
     * 患者主索引
     */
    private String empi;
    /**
     * 用户加密信息
     */
    private String imaHeader;
    /**
     * 前后端调用链id
     */
    private String chainId;
    /**
     * 业务处理方法名称
     */
    private String classMethod;
    /**
     * 本次调用消耗的时间（ms）
     */
    private Long timeCost;
    /**
     * 业务api接口，由此可以区分关注的业务类型
     */
    private String reqUrl;
    /**
     * HTTP请求的方法名称
     */
    private String reqMethod;
    /**
     * 用户代理
     */
    private String reqUserAgent;
    /**
     * 入参
     */
    private String reqInput;
    /**
     * 出参
     */
    private String reqOutput;


    @Getter
    public class Msg implements Serializable {

        private static final long serialVersionUID = 4845737301640856459L;

        /**
         * 入参
         */
        private final String input;
        /**
         * 出参
         */
        private final String output;


        public Msg() {
            this.input = PointLog.this.getReqInput();
            this.output = PointLog.this.getReqOutput();
        }
    }

}
