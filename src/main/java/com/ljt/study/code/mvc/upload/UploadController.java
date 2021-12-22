package com.ljt.study.code.mvc.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;

/**
 * @author LiJingTang
 * @date 2021-12-20 15:37
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private MultipartConfigElement multipartConfigElement;

    @PostConstruct
    public void init() {
        System.out.println(multipartConfigElement);
    }

    @PostMapping
    public String up(MultipartFile file) {
        return "OK";
    }

}
