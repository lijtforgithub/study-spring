package com.ljt.study.slice.server.service;

import com.ljt.study.slice.dto.UploadRequestDTO;
import com.ljt.study.slice.dto.UploadResponseDTO;

/**
 * @author LiJingTang
 * @date 2022-11-30 10:40
 */
public interface UploadService {

    UploadResponseDTO upload(UploadRequestDTO requestDTO);

}
