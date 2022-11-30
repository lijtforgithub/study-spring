package com.ljt.study.slice.server.properties;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author LiJingTang
 * @date 2022-11-30 13:31
 */
@Data
@Component
public class UploadProperties {

    private Integer queueMaxSize;

}
