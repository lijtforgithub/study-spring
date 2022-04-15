package com.ljt.study.reqlog;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ljt.study.reqlog.log.LogConstant;
import com.ljt.study.reqlog.api.MedicalAiException;
import com.ljt.study.reqlog.enums.ApiPathEnum;
import com.ljt.study.reqlog.enums.SystemEnum;
import com.ljt.study.reqlog.retry.RetryEnum;
import com.ljt.study.reqlog.retry.RetryRcException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static javax.servlet.http.HttpServletResponse.*;

/**
 * @author jtli3
 * @date 2021-12-31 14:10
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
@Component
class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    /**
     * 需要重试调用的异常码
     */
    private static final Set<Integer> RETRY_CODE = Sets.newHashSet(SC_INTERNAL_SERVER_ERROR, SC_BAD_GATEWAY, SC_SERVICE_UNAVAILABLE, SC_GATEWAY_TIMEOUT);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        RequestLog requestLog = RequestLogHolder.get();
        RequestLog.LogItem item = requestLog.new LogItem();
        item.setStartDateTime(System.currentTimeMillis());
        requestLog.getItemList().add(item);

        URI uri = request.getURI();
        RetryContext context = getContext(uri);

        item.setSystem(context.getSystem().getCode())
                .setPath(SystemHelper.getFullPath(uri))
                .setReqParam(getReqParam(uri))
                .setReqBody(new String(body));

        try (ClientHttpResponse response = context.isRetry()
                ? ((RestTemplateInterceptor) AopContext.currentProxy()).retryCall(request, body, execution, context)
                : execution.execute(request, body);
             ClientHttpResponseWrapper responseWrapper = new ClientHttpResponseWrapper(response)) {

            item.setStatusCode(responseWrapper.getRawStatusCode());
            context.getSystem().handleLog(new String(IOUtils.toByteArray(responseWrapper.getBody())), item);
            return responseWrapper;
        } catch (RetryRcException e) {
            log.warn("{} = {}", uri, e.getMessage());
            ClientHttpResponseWrapper responseWrapper = new ClientHttpResponseWrapper(e.getResponse());
            item.setStatusCode(responseWrapper.getRawStatusCode());
            context.getSystem().handleLog(new String(IOUtils.toByteArray(responseWrapper.getBody())), item);
            return responseWrapper;
        } catch (IOException e) {
            item.setStatusCode(SC_SERVICE_UNAVAILABLE).setErrorMsg(e.getMessage());
            MedicalAiException.ChainItem chainItem = new MedicalAiException.ChainItem(item.getPath(), SC_SERVICE_UNAVAILABLE, e.getMessage());
            RequestLogHolder.get().getChainError().add(chainItem);
            throw e;
        } finally {
            item.setEndDateTime(System.currentTimeMillis());
            item.setCostMillis(item.getEndDateTime() - item.getStartDateTime());
        }
    }

    @Retryable(value = { IOException.class, RetryRcException.class}, backoff = @Backoff(value = 500, multiplier = 1.5))
    public ClientHttpResponse retryCall(HttpRequest request, byte[] body, ClientHttpRequestExecution execution, RetryContext context) throws IOException {
        log.debug("{} ... retryCall .......... ", request.getURI());

        ClientHttpResponse response = execution.execute(request, body);
        int code = response.getRawStatusCode();
        if (RETRY_CODE.contains(code)) {
            throw new RetryRcException("异常码重试：" + code, response);
        }

        return response;
    }

    /**
     * 重试依然失败的接口保存请求信息
     */
    @Recover
    public ClientHttpResponse recover(Exception e, HttpRequest request, byte[] body, ClientHttpRequestExecution execution, RetryContext context) throws Exception {
        if (context.isSave()) {
            // 保存数据库
        } else {
            log.warn("尝试后依然请求失败：{}", LogConstant.decodeUrl(request.getURI().toString()));
        }

        throw e;
    }

    private static String getReqParam(URI uri) {
        MultiValueMap<String, String> params = UriComponentsBuilder.fromUri(uri).build().getQueryParams();
        if (CollectionUtils.isEmpty(params)) {
            return null;
        }
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(params.size());
        params.forEach((k, v) -> {
            List<String> list = v.stream().map(s -> {
                try {
                    return URLDecoder.decode(s, StandardCharsets.UTF_8.name());
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            }).collect(Collectors.toList());
            map.put(k, list.size() == 1 ? list.get(0) : list);
        });
        return JSON.toJSONString(map);
    }

    private static RetryContext getContext(URI uri) {
        RetryContext context = new RetryContext();
        ApiPathEnum pathEnum = SystemHelper.getPathEnum(uri);

        if (Objects.nonNull(pathEnum)) {
            context.setSystem(pathEnum.getSystem());
            context.setRetry(pathEnum.getRetry());
            context.setRetryDesc(pathEnum.getDesc());
        } else {
            context.setSystem(SystemHelper.getSystemEnum(uri));
            context.setRetry(RequestLogHolder.get().getRetry());
            context.setRetryDesc(RequestLogHolder.get().getRetryDesc());
        }

        return context;
    }


    @Setter
    @Getter
    private static class RetryContext {
        private RetryEnum retry;
        private String retryDesc;
        private SystemEnum system;

        private boolean isRetry() {
            return Objects.nonNull(retry);
        }

        private boolean isSave() {
            return isRetry() && RetryEnum.RETRY_SAVE == retry ;
        }
    }

    private static class ClientHttpResponseWrapper implements ClientHttpResponse {

        private final ClientHttpResponse response;
        private byte[] body;

        private ClientHttpResponseWrapper(ClientHttpResponse response) {
            this.response = response;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return this.response.getStatusCode();
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.response.getRawStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.response.getStatusText();
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.response.getHeaders();
        }

        @Override
        public InputStream getBody() throws IOException {
            if (this.body == null) {
                this.body = StreamUtils.copyToByteArray(this.response.getBody());
            }
            return new ByteArrayInputStream(this.body);
        }

        @Override
        public void close() {
            this.response.close();
        }
    }

}
