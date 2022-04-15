package com.ljt.study.reqlog.api;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Objects;

/**
 * @author jtli3
 * @date 2022-03-15 10:30
 */
class MedicalAiExceptionInterceptor implements ClientHttpRequestInterceptor {

    @SneakyThrows
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) {
        try {
            ClientHttpResponse response = execution.execute(request, body);

            if (MedicalAiExceptionEnum.OK.getCode() != response.getRawStatusCode()) {
                String respBody = new String(IOUtils.toByteArray(response.getBody()));
                Response<?> resp = JSON.parseObject(respBody, Response.class);
                MedicalAiException e = new MedicalAiException(response.getRawStatusCode(), resp.getMsg());

                // 内部接口调用异常
                if (MedicalAiExceptionEnum.CHAIN.getCode() == e.getCode() && resp.getData() != null) {
                    // 接口返回值
                    if (Objects.nonNull(resp.getData())) {
                        if (resp.getData() instanceof String) {
                            e.setData((String) resp.getData());
                        } else {
                            e.setData(JSON.toJSONString(resp.getData()));
                        }
                    }

                    // 异常详细信息
                    if (StringUtils.isNotBlank(resp.getMsg())) {
                        e.setChainError(JSON.parseArray(resp.getMsg(), MedicalAiException.ChainItem.class));
                    }
                }

                throw e;
            }
            return response;
        } catch (IOException e) {
            throw new MedicalAiException(MedicalAiExceptionEnum.SERVER_ERROR.getCode(), e.getMessage());
        }
    }

}