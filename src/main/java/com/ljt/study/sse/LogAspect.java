package com.ljt.study.sse;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static com.ljt.study.sse.BusinessTypeEnm.COMMON;
import static com.ljt.study.sse.LogConstant.LOG_TYPE;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getM() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void post() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void put() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void patch() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void delete() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void request() {
    }


    @Around("getM() || post() || put() || patch() || delete() || request()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(LogAspect.class.getSimpleName());

        Signature signature = joinPoint.getStaticPart().getSignature();
        PointLog pointLog = new PointLog();
        pointLog.setLogType(LOG_TYPE)
                .setTimestampe(System.currentTimeMillis())
                .setClassMethod(String.format("%s#%s", signature.getDeclaringTypeName(), signature.getName()))
                .setBusinessType(COMMON.getCode());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (Objects.nonNull(attributes)) {
            HttpServletRequest request = attributes.getRequest();
            pointLog.setClientIp(getIpAddress(request))
                    .setReqUserAgent(request.getHeader("User-Agent"))
                    .setReqUrl(request.getRequestURI())
                    .setReqMethod(request.getMethod())
                    .setReqInput("");
            joinPoint.get
            System.out.println(Arrays.toString(joinPoint.getArgs()));
        }

        return joinPoint.proceed();
    }

//    private String getReqInput(HttpServletRequest request) {
//        Map<String, String[]> parameterMap = request.getParameterMap();
//
//        if (Objects.nonNull(parameterMap)) {
//            request.getInputStream()
//        }
//
//        return null;
//    }


    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (isValid(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            return Stream.of(ip.split(",")).filter(item -> !UN_KNOW.equalsIgnoreCase(item)).findFirst().orElse(ip);
        }

        for (String header : IP_HEADERS) {
            ip = request.getHeader(header);
            if (isValid(ip)) {
                return ip;
            }
        }

        ip = request.getRemoteAddr();
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (Exception e) {
                log.error("IP解析异常", e);
            }
        }

        return ip;
    }

    private static boolean isValid(String ip) {
        return StringUtils.isNotBlank(ip) && !UN_KNOW.equalsIgnoreCase(ip);
    }

    private static final String UN_KNOW = "unKnown";
    private static final String[] IP_HEADERS = {"X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

}
