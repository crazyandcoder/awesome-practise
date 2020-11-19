package com.crazyandcoder.university.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyandcoder.university.entity.TopUniversityDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityListInfo;
import com.crazyandcoder.university.entity.TopUniversityProfessionDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityProfessionListInfo;
import com.crazyandcoder.university.model.Response;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liji
 * @since 2020-07-14
 */
public interface ITopUniversityProfessionService extends IService<TopUniversityProfessionListInfo> {

    /**
     * 通过schoolId来获取高校详情
     *
     * @param specialId
     * @return
     */
    Response<TopUniversityProfessionDetailInfo> selectTopUniversityProfessionDetailInfoById(String specialId);


    /**
     * 分页专业列表
     *
     * @param keyword 支持根据专业名称模糊搜索
     * @param page    分页参数
     * @return
     */
    Response<Page<TopUniversityProfessionListInfo>> getProfessionListPage(String keyword, String level1, String level3, Page<TopUniversityProfessionListInfo> page);

    /**
     * 查询热门学校
     *
     * @param provinceId
     * @param type
     * @param schoolType
     * @param sortType
     * @param page
     * @return
     */
    Response<Page<TopUniversityProfessionListInfo>> getSchoolProfessionHotList(String level1,  Page<TopUniversityProfessionListInfo> page);


}
