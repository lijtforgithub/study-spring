package com.ljt.study.sse;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static com.ljt.study.sse.BusinessTypeEnm.COMMON;
import static com.ljt.study.sse.LogConstant.*;

@Slf4j
@Aspect
class LogAspect {

    @Pointcut("within(com.ljt.study..*)")
    public void pack() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getM() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void post() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void put() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void patch() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void delete() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void request() {}

    @Pointcut("getM() || post() || put() || patch() || delete() || request()")
    public void method() {}


    @Around("pack() && method()")
    Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(LogAspect.class.getSimpleName());
        PointLog pointLog = initPointLog(joinPoint);
        MdcHelper.putPointLog(pointLog);

        try {
            Object result = joinPoint.proceed();

            if (Objects.nonNull(result)) {
                if (BeanUtils.isSimpleValueType(result.getClass())) {
                    pointLog.setReqOutput(result.toString());
                } else {
                    pointLog.setReqOutput(JSON.toJSONString(result));
                }
            }

            stopWatch.stop();
            pointLog.setTimeCost(stopWatch.getLastTaskTimeMillis());

            logInfo(pointLog);

            return result;
        } catch (Throwable e) {
            log.error("{} [{}()] 请求异常 入参：{}", pointLog.getReqUrl(), pointLog.getClassMethod(), pointLog.getReqInput());
            throw e;
        } finally {
            MdcHelper.clearPointLog();
        }
    }

    private void logInfo(PointLog pointLog) {
        MdcHelper.setReqOutput(pointLog.getReqOutput());
        MdcHelper.setTimeCost(pointLog.getTimeCost());
        MdcHelper.setBusinessType(COMMON.getCode());
        PointLogger.info(COMMON.getDesc());
    }

    private PointLog initPointLog(ProceedingJoinPoint joinPoint) {
        PointLog pointLog = new PointLog();
        Signature signature = joinPoint.getStaticPart().getSignature();
        pointLog.setLogType(LOG_TYPE)
                .setTimestampe(System.currentTimeMillis())
                .setClassMethod(String.format("%s#%s", signature.getDeclaringTypeName(), signature.getName()))
                .setBusinessType(COMMON.getCode())
                .setReqInput(getMethodParameter(joinPoint));

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (Objects.nonNull(attributes)) {
            HttpServletRequest request = attributes.getRequest();
            pointLog.setClientIp(getIpAddress(request))
                    .setReqUserAgent(request.getHeader(USER_AGENT))
                    .setChainId(request.getHeader(CHAIN_ID))
                    .setReqUrl(request.getRequestURI())
                    .setReqMethod(request.getMethod());
        }

        return pointLog;
    }

    private String getMethodParameter(ProceedingJoinPoint joinPoint) {
        Map<String, Object> map = Maps.newHashMap();
        Signature signature = joinPoint.getStaticPart().getSignature();

        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            String[] names = methodSignature.getParameterNames();
            Class<?>[] types = methodSignature.getParameterTypes();

            for (int i = 0; i < names.length; i++) {
                String name = names[i];
                Class<?> type = types[i];
                Object arg = joinPoint.getArgs()[i];

                if (BeanUtils.isSimpleValueType(type)) {
                    map.put(name, arg);
                } else if (MultipartResolutionDelegate.isMultipartArgument(MethodParameter.forExecutable(methodSignature.getMethod(), i))) {
                    String fileName = getFileName(type, arg);
                    map.put(name, fileName);
                } else {
                    map.putAll(getProperty(type, arg));
                }
            }
        }

        return JSON.toJSONString(map);
    }

    private String getFileName(Class<?> type, Object arg) {
        if (MultipartFile.class == type) {
            return  ((MultipartFile) arg).getOriginalFilename();
        } else if (Part.class == type) {
            return ((Part) arg).getSubmittedFileName();
        }

        return "MultiFile";
    }

    private Map<String, Object> getProperty(Class<?> type, Object arg) {
        PropertyDescriptor[] propDescriptors = BeanUtils.getPropertyDescriptors(type);
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(propDescriptors.length);

        for (PropertyDescriptor propDescriptor : propDescriptors) {
            Method method = propDescriptor.getReadMethod();

            if (Object.class == method.getDeclaringClass()) {
                continue;
            }

            try {
                Object value = method.invoke(arg);
                map.put(propDescriptor.getName(), value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.error("获取属性 {}#{} 异常", type.getName(), method.getName());
            }
        }

        return map;
    }


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
