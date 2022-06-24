package com.ljt.study.web;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author LiJingTang
 * @date 2022-06-21 11:16
 */
@Validated
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/param")
    public void param(@NotBlank String key) {

    }

}
