package com.ljt.study.reqlog;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.ljt.study.log.*;
import com.ljt.study.reqlog.api.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Phaser;

import static com.ljt.study.log.LogConstant.REQ_POINT;
import static com.ljt.study.reqlog.api.MedicalAiExceptionEnum.CHAIN;
import static com.ljt.study.reqlog.api.MedicalAiExceptionEnum.SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_OK;

/**
 * 与GlobalExceptionHandler结合使用
 *
 * @author jtli3
 * @date 2022-01-06 20:02
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
class RequestLogFilter implements Filter {

    @Autowired
    private PathMatcher pathMatcher;
    @Autowired
    private DefaultErrorAttributes defaultErrorAttributes;

    private static final Set<String> EXCLUDE_URLS = Sets.newHashSet("/doc.html", "/favicon.ico", "/v2/api-docs",
            "/swagger-resources", "/swagger-resources/configuration/ui");
    private static final Set<String> EXCLUDE_PATTERN = Sets.newHashSet("/**/*.js", "/**/*.css", "/actuator/**", "/{version}/pb/statistics/**");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        if (isSkip(servletRequest)) {
            chain.doFilter(request, response);
            return;
        }

        String uri = servletRequest.getRequestURI();
        log.info("请求日志Filter开始 {}", servletRequest.getRequestURI());
        RepeatableRequestWrapper requestWrapper = new RepeatableRequestWrapper(servletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
        RequestLog requestLog = RequestLogHolder.get();

        try {
            requestLog.setPath(LogConstant.decodeUrl(requestWrapper.getRequestURI()))
                    .setReqParam(CollectionUtils.isEmpty(requestWrapper.getParameterMap()) ? null : JSON.toJSONString(requestWrapper.getParameterMap()))
                    .setReqBody(new String(IOUtils.toByteArray(requestWrapper.getInputStream())));

            chain.doFilter(requestWrapper, responseWrapper);

            requestLog.setStatusCode(responseWrapper.getStatusCode());
            final String respBody = new String(responseWrapper.getContentAsByteArray());

            if (SC_OK == requestLog.getStatusCode()) {
                // 调用其他平台出现异常
                if (!CollectionUtils.isEmpty(requestLog.getChainError())) {
                    requestLog.setStatusCode(CHAIN.getCode())
                            .setErrorMsg(JSON.toJSONString(requestLog.getChainError()));
                    Response<String> resp = new Response<>(String.valueOf(requestLog.getStatusCode()), requestLog.getErrorMsg(), respBody);
                    reWriteResp(response, requestLog.getStatusCode(), resp);
                } else {
                    requestLog.setResp(respBody);
                    responseWrapper.copyBodyToResponse();
                }
            } else {
                Response<?> resp = JSON.parseObject(respBody, Response.class);
                String msg = resp.getMsg();
                if (StringUtils.isBlank(msg)) {
                    Throwable e = defaultErrorAttributes.getError(new ServletWebRequest(requestWrapper));
                    if (Objects.nonNull(e)) {
                        msg = e.getMessage();
                    }
                }
                requestLog.setErrorMsg(msg);
                responseWrapper.copyBodyToResponse();
            }
        } catch (Exception e) {
            log.error("请求异常" + uri, e);
            requestLog.setStatusCode(SERVER_ERROR.getCode()).setErrorMsg(e.getMessage());
            Response<?> resp = new Response<>(String.valueOf(requestLog.getStatusCode()), requestLog.getErrorMsg(), SERVER_ERROR.getDesc());
            reWriteResp(response, requestLog.getStatusCode(), resp);
        } finally {
            asyncWait(uri, requestLog);
            logInfo(request);
        }

        log.info("请求日志Filter结束 {}", uri);
    }

    private boolean isSkip(HttpServletRequest request) {
        String uri = request.getRequestURI();

        if (EXCLUDE_URLS.contains(uri)) {
            return true;
        } else if (EXCLUDE_PATTERN.stream().anyMatch(s -> pathMatcher.match(s, uri))
                || MultipartResolutionDelegate.isMultipartRequest(request)) {
            EXCLUDE_URLS.add(uri);
            return true;
        }
        return false;
    }

    /**
     * 等待异步线程
     */
    private void asyncWait(String uri, RequestLog requestLog) {
        Phaser phaser = requestLog.getAsyncPhaser();
        if (Objects.nonNull(phaser)) {
            // 存在异步调用等待方法执行完成
            phaser.awaitAdvance(0);
            log.info("内部异步调用执行完成 {}", uri);
        }
    }

    /**
     * 输出埋点日志
     */
    private void logInfo(ServletRequest request) {
        RequestLog requestLog = RequestLogHolder.get();
        requestLog.setEndDateTime(System.currentTimeMillis());

        Object obj = request.getAttribute(REQ_POINT);
        if (Objects.nonNull(obj) && obj instanceof PointLog) {
            PointLog pointLog = (PointLog) obj;
            pointLog.setTimeCost(requestLog.getEndDateTime() - requestLog.getStartDateTime());
            MdcHelper.putPointLog(pointLog);
        }

        PointLogger.info(BusinessTypeEnm.AI, JSON.toJSONString(requestLog));
        RequestLogHolder.remove();
        MdcHelper.clearPointLog();
    }

    /**
     * 重写响应信息
     */
    private void reWriteResp(ServletResponse response, int statusCode, Object msg) {
        response.reset();
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setStatus(statusCode);
        response.setContentType("text/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            String text = JSON.toJSONString(msg);
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            log.error("重写异常信息", e);
        }
    }


    private static class RepeatableRequestWrapper extends HttpServletRequestWrapper {

        private final byte[] body;
        private final Charset charset;

        @SneakyThrows
        public RepeatableRequestWrapper(HttpServletRequest request, Charset charset) {
            super(request);
            body = IOUtils.toByteArray(request.getInputStream());
            this.charset = charset;
        }

        public RepeatableRequestWrapper(HttpServletRequest request) {
            this(request, StandardCharsets.UTF_8);
        }

        @Override
        public ServletInputStream getInputStream() {
            return new RepeatableInputStream();
        }

        @Override
        public BufferedReader getReader() {
            return new BufferedReader(new InputStreamReader(getInputStream(), charset));
        }


        private class RepeatableInputStream extends ServletInputStream {

            private final ByteArrayInputStream byteArrayInputStream;

            @Override
            public synchronized void reset() {
                byteArrayInputStream.reset();
            }

            @Override
            public synchronized void mark(int readLimit) {
                byteArrayInputStream.mark(readLimit);
            }

            public RepeatableInputStream() {
                byteArrayInputStream = new ByteArrayInputStream(body);
            }

            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException("不支持监听");
            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean markSupported() {
                return byteArrayInputStream.markSupported();
            }
        }

    }

}
