package com.crazyandcoder.university.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyandcoder.university.entity.TopUniversitySelectConfigInfo;
import com.crazyandcoder.university.model.Response;

public interface ITopUniversityInfoSelectConfigService extends IService<TopUniversitySelectConfigInfo> {

    /**
     * 获取高校筛选条件
     *
     * @return
     */
    Response<TopUniversitySelectConfigInfo> getSelectConfigs();
}
