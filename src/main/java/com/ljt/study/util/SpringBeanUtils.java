package com.ljt.study.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiJingTang
 * @date 2021-07-01 10:20
 */
public class SpringBeanUtils extends BeanUtils {

    private static final JacksonBeanConvertor BEAN_CONVERTOR = new JacksonBeanConvertor();

    /**
     * 任意 Bean 类型转换，深度转换（copyProperties 方法为浅拷贝）
     *
     * @param fromValue   原始 Bean
     * @param toValueType 目标类型
     * @return T
     */
    public static <T> T copyDeeply(Object fromValue, Class<T> toValueType) {
        return BEAN_CONVERTOR.convert(fromValue, toValueType);
    }

    /**
     * 拷贝列表，需要指定目标列表的元素类型
     *
     * @param sources 原始 Bean 的列表
     * @param target  目标列表元素的类型
     * @return <E>
     */
    public static <E> List<E> copyTo(List<?> sources, Class<E> target) {
        if (CollectionUtils.isEmpty(sources)) {
            return new ArrayList<>();
        }

        List<E> list = new ArrayList<>(sources.size());
        for (Object source : sources) {
            if (source == null) {
                list.add(null);
            } else {
                list.add(copyDeeply(source, target));
            }
        }
        return list;
    }

    private static class JacksonBeanConvertor {

        private static final ObjectMapper OBJECT_MAPPER;

        static {
            OBJECT_MAPPER = new ObjectMapper();
            // 指定时间格式
            OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            // 检测所有字段，包括私有的
            OBJECT_MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            // 序列化时忽略空属性
            OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // 反序列化时忽略不一致成员变量
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        public <T> T convert(Object fromValue, Class<T> toValueType) {
            return OBJECT_MAPPER.convertValue(fromValue, toValueType);
        }

        public <T> T convert(Object fromValue, Type typeOfT) {
            JavaType toValueType = OBJECT_MAPPER.getTypeFactory().constructType(typeOfT);
            return OBJECT_MAPPER.convertValue(fromValue, toValueType);
        }
    }

}
