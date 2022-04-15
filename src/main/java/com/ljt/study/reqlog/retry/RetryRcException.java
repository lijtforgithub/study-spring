package com.ljt.study.reqlog.retry;

import lombok.Getter;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author jtli3
 * @date 2022-02-23 14:37
 */
@Getter
public class RetryRcException extends RuntimeException {

    private static final long serialVersionUID = 6197917427198227774L;

    private final ClientHttpResponse response;

    public RetryRcException(String message, ClientHttpResponse response) {
        super(message);
        this.response = response;
    }

}
