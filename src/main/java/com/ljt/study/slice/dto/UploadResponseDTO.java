package com.ljt.study.slice.dto;

import lombok.Data;

/**
 * @author LiJingTang
 * @date 2022-11-30 10:42
 */
@Data
public class UploadResponseDTO {

    private String id;
    private String originName;
    private Integer chunkNo;
    private Boolean completed;

}
