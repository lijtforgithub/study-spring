package com.ljt.study.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author LiJingTang
 * @date 2021-06-23 17:51
 */
@Accessors(chain = true)
@Data
public class Log {

    @TableId
    private Integer id;
    private String content;

}
