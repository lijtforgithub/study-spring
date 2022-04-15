package com.ljt.study.reqlog.log;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author jtli3
 * @date 2022-02-25 15:36
 */
@Slf4j
public class LogConstant {

    private LogConstant() {}

    public static final Integer LOG_TYPE = 1;

    public static final String USER_AGENT = "User-Agent";
    public static final String CHAIN_ID = "chain-id";
    public static final String IMA_HEADER = "ima-header";

    public static final String REQ_POINT = "_request_point_";

    public static String decodeUrl(String url) {
        try {
            url = URLDecoder.decode(url, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.error("decodeUrl", e);
        }

        return url;
    }

}
