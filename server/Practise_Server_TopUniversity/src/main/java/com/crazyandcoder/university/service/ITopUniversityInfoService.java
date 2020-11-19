package com.crazyandcoder.university.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityDetailMoreInfo;
import com.crazyandcoder.university.entity.TopUniversityListInfo;
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
public interface ITopUniversityInfoService extends IService<TopUniversityListInfo> {

    /**
     * 通过schoolId来获取高校详情
     *
     * @param schoolId
     * @return
     */
    Response<TopUniversityDetailMoreInfo> selectTopUniversityDetailMoreInfoById(String schoolId);

    /**
     * 通过schoolId来获取高校详情
     *
     * @param schoolId
     * @return
     */
    Response<TopUniversityDetailInfo> selectTopUniversityDetailInfoById(String schoolId);


    /**
     * 分页查询用户
     *
     * @param topUniversityName 支持根据高校名称模糊搜索
     * @param page              分页参数
     * @return
     */
    Response<Page<TopUniversityListInfo>> getUserPage(String f211, String f985, String keyword, String provinceId, String type, String schoolType, Page<TopUniversityListInfo> page);

    /**
     * 查询热门学校
     *
     * @param provinceId
     * @param type
     * @param schoolType
     * @param page
     * @return
     */
    Response<Page<TopUniversityListInfo>> getSchoolHotList(String provinceId, String type, String schoolType, Page<TopUniversityListInfo> page);


}
