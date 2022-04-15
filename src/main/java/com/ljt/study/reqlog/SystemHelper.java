package com.ljt.study.reqlog;

import com.google.common.collect.Maps;
import com.ljt.study.reqlog.enums.ApiPathEnum;
import com.ljt.study.reqlog.enums.SystemEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.ljt.study.reqlog.enums.SystemEnum.MDM;
import static com.ljt.study.reqlog.enums.SystemEnum.UN_KNOW;

/**
 * @author jtli3
 * @date 2022-01-14 10:42
 */
@Slf4j
@Component
public class SystemHelper {


    private static final Map<SystemEnum, Set<String>> URL_MAP = Maps.newHashMapWithExpectedSize(2);
    private static final Map<String, ApiPathEnum> PATH_MAP = Maps.newHashMap();

    @PostConstruct
    public void init() {
//        URL_MAP.put(MDM, mdmConfigProperties.getGatewayConfig().values().stream().map(GatewayConfig::getServerAddr).collect(Collectors.toSet()));
//        URL_MAP.put(ICDSS, Sets.newHashSet(icdssProperties.getUrl()));

        log.info("初始化请求地址缓存：{}", URL_MAP);

        for (ApiPathEnum pathEnum : ApiPathEnum.values()) {
            URL_MAP.get(pathEnum.getSystem()).forEach(url -> PATH_MAP.put(url + pathEnum.getPath(), pathEnum));
        }

        log.info("初始化系统接口缓存：{}", PATH_MAP);
    }

    public static String getFullPath(URI uri) {
        String str = uri.toString();
        int index = str.indexOf('?');
        if (index != -1) {
            str = str.substring(0, index);
        }

        return str;
    }

    public static ApiPathEnum getPathEnum(URI uri) {
        return PATH_MAP.get(getFullPath(uri));
    }

    public static SystemEnum getSystemEnum(URI uri) {
        String str = uri.toString();

        for (Map.Entry<SystemEnum, Set<String>> entry : URL_MAP.entrySet()) {
            boolean match = entry.getValue().stream().anyMatch(str::startsWith);
            if (match) {
                return entry.getKey();
            }
        }

        return UN_KNOW;
    }

    public static String getUrl(ApiPathEnum pathEnum) {
        Objects.requireNonNull(pathEnum, "请求枚举为空");

        switch (pathEnum.getSystem()) {
            case UN_KNOW:
            case MDM:
                log.warn("{} 平台不支持", MDM.getDesc());
                return null;
            case ICDSS:
            default:
                return URL_MAP.get(pathEnum.getSystem()).stream().findFirst() + pathEnum.getPath();
        }
    }

}
