package com.crazyandcoder.university.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyandcoder.university.entity.AppUpdate;
import com.crazyandcoder.university.model.Response;

/**
 * 用户信息
 */
public interface ICrazyandcoderUpdateService extends IService<AppUpdate> {


    Response<AppUpdate> getUpdateInfo(String pkg, int versionCode);
}
