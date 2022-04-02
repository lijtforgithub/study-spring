package com.ljt.study.socketio.spring.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author LiJingTang
 * @date 2022-04-02 13:56
 */
@Data
@ConfigurationProperties(prefix = "socketio")
public class SocketProperties {

    private Integer port = 9090;
    private Integer bossThreads = 1;
    private Integer workerThreads = 100;
    private Boolean isAllowCustomRequests = true;
    private Integer upgradeTimeout = 1000_0000;
    private Integer pingTimeout = 6000_0000;
    private Integer pingInterval = 30_0000;

}
