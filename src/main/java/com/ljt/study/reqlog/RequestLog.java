package com.ljt.study.reqlog;

import com.ljt.study.reqlog.api.MedicalAiException;
import com.ljt.study.reqlog.retry.RetryEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Phaser;

/**
 * @author jtli3
 * @date 2022-01-14 9:04
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class RequestLog extends HttpLog {
    /**
     * 内部请求接口明细
     */
    private List<LogItem> itemList;


    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public class LogItem extends HttpLog {

        /**
         * 调用系统
         */
        private String system;
        /**
         * 辅诊平台耗时微秒
         */
        private Long costMillis;

    }

    private transient RetryEnum retry;
    private transient String retryDesc;

    private transient List<MedicalAiException.ChainItem> chainError;

    private transient volatile Phaser asyncPhaser;

    public synchronized Phaser initPhaser() {
        if (Objects.isNull(asyncPhaser)) {
            asyncPhaser = new Phaser();
        }

        return asyncPhaser;
    }

}

@Data
@Accessors(chain = true)
class HttpLog {

    /**
     * 请求路径
     */
    private String path;
    /**
     * 请求参数
     */
    private String reqParam;
    /**
     * 请求体
     */
    private String reqBody;
    /**
     * 响应数据
     */
    private String resp;
    /**
     * 响应状态码
     */
    private Integer statusCode;
    /**
     * 异常信息
     */
    private String errorMsg;
    /**
     * 请求开始时间
     */
    private Long startDateTime;
    /**
     * 请求结束时间
     */
    private Long endDateTime;

}
