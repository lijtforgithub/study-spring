package com.ljt.study.slice.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author LiJingTang
 * @date 2022-11-30 10:22
 */
@Data
public class UploadRequestDTO {

    @NotBlank
    private String id;

    @NotNull
    private Integer chunkNum;
    @NotNull
    private Integer chunkNo;
    @NotNull
    private Long chunkSize;

    @NotBlank
    private String originName;
    @NotNull
    private MultipartFile file;

}
