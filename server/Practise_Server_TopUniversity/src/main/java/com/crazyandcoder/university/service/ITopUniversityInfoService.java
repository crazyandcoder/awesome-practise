package com.crazyandcoder.university.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyandcoder.university.model.Response;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liji
 * @since 2020-07-14
 */
public interface ITopUniversityInfoService extends IService<TopUniversityInfo> {

    /**
     * 通过schoolId来获取高校详情
     *
     * @param schoolId
     * @return
     */
    public Response<TopUniversityInfo> getUniversityInfoById(String schoolId);


    /**
     * 分页查询用户
     *
     * @param topUniversityName 支持根据高校名称模糊搜索
     * @param page   分页参数
     * @return
     */
    public Response<Page<TopUniversityInfo>> getUserPage(String topUniversityName, Page<TopUniversityInfo> page);

}
