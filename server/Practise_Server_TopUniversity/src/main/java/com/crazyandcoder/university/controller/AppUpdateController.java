package com.crazyandcoder.university.controller;

import com.crazyandcoder.university.entity.AppUpdate;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.service.ICrazyandcoderUpdateService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "app升级更新接口")
@RestController
@RequestMapping("/crazyandcoder/update")
public class AppUpdateController {

    private static final Logger logger = LoggerFactory.getLogger(AppUpdateController.class);


    @Autowired
    ICrazyandcoderUpdateService updateService;

    /**
     * app更新申请
     *
     * @param pkg
     * @param versionCode
     * @return
     */
    @GetMapping("/info")
    public Response<AppUpdate> getUpdateInfo(String pkg, int versionCode) {
        return updateService.getUpdateInfo(pkg, versionCode);
    }
}
