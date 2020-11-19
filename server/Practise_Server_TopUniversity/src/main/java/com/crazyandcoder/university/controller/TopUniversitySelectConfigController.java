package com.crazyandcoder.university.controller;

import com.crazyandcoder.university.entity.TopUniversitySelectConfigInfo;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.service.ITopUniversityInfoSelectConfigService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置类
 */
@Api(tags = "全国高校接口")
@RestController
@RequestMapping("/top-university-info/config")
public class TopUniversitySelectConfigController {


    private static final Logger logger = LoggerFactory.getLogger(TopUniversitySelectConfigController.class);

    @Autowired
    ITopUniversityInfoSelectConfigService topUniversityInfoSelectConfigService;

    @GetMapping("/getSelectConfig")
    public Response<TopUniversitySelectConfigInfo> getSelectConfig() {
        return topUniversityInfoSelectConfigService.getSelectConfigs();
    }

}
