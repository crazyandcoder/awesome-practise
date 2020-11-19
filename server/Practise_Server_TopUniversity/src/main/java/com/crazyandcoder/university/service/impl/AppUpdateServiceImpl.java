package com.crazyandcoder.university.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyandcoder.university.entity.AppUpdate;
import com.crazyandcoder.university.mapper.AppUpdateMapper;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.service.ICrazyandcoderUpdateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUpdateServiceImpl extends ServiceImpl<AppUpdateMapper, AppUpdate> implements ICrazyandcoderUpdateService {


    private static final Logger logger = LoggerFactory.getLogger(AppUpdateServiceImpl.class);

    @Autowired
    AppUpdateMapper appUpdateMapper;


    @Override
    public Response<AppUpdate> getUpdateInfo(String pkg, int versionCode) {
        if (StringUtils.isBlank(pkg)) {
            logger.error("app包名为空");
            return Response.failResult("app包名为空");
        }
        if (0 == versionCode) {
            logger.error("app版本号等于0");
            return Response.failResult("app版本号等于0");
        }
        AppUpdate result = appUpdateMapper.getUpdateInfo(pkg,versionCode);
        if (result == null) {
            logger.info("暂无更新");
            return Response.successResult("暂无更新");
        } else {
            logger.info("有更新");
            return Response.successResult("有最新更新内容", result);
        }
    }
}
