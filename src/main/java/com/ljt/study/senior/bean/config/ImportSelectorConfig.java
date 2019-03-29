package com.ljt.study.senior.bean.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ljt.study.senior.bean.custom.CustomImportSelector;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午5:38:40
 */
@Configuration
@Import(CustomImportSelector.class)
public class ImportSelectorConfig {

}
