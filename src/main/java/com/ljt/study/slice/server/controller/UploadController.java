package com.ljt.study.slice.server.controller;

import com.ljt.study.slice.dto.UploadRequestDTO;
import com.ljt.study.slice.dto.UploadResponseDTO;
import com.ljt.study.slice.server.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiJingTang
 * @date 2022-11-30 10:20
 */
@RestController
@RequestMapping("/slice/upload")
public class UploadController {

    @Autowired
    private UploadService fileUploadService;

    @PostMapping
    public UploadResponseDTO upload(@Validated UploadRequestDTO requestDTO) {
        return fileUploadService.upload(requestDTO);
    }

}
