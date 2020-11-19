package com.crazyandcoder.university.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityDetailMoreInfo;
import com.crazyandcoder.university.entity.TopUniversityListInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author liji
 * @since 2020-07-14
 */
public interface TopUniversityInfoMapper extends BaseMapper<TopUniversityListInfo> {


    /**
     * 分页查询高校列表
     *
     * @param f211
     * @param f985
     * @param keyword
     * @param province_id
     * @param type
     * @param keyword
     * @param page
     * @return
     */
    public List<TopUniversityListInfo> selectTopUniversitiesPage(@Param("f211") String f211,
                                                                 @Param("f985") String f985,
                                                                 @Param("keyword") String keyword,
                                                                 @Param("province_id") String province_id,
                                                                 @Param("type") String type,
                                                                 @Param("school_type") String school_type,
                                                                 Page<TopUniversityListInfo> page);


    /**
     * 分页查询热门高校列表
     *
     * @param f211
     * @param f985
     * @param keyword
     * @param province_id
     * @param type
     * @param school_type
     * @param page
     * @return
     */
    public List<TopUniversityListInfo> selectTopUniversitiesHotPage(
            @Param("province_id") String province_id,
            @Param("type") String type,
            @Param("school_type") String school_type,
            Page<TopUniversityListInfo> page);

    /**
     * 查询高校详情
     *
     * @param school_id
     * @return
     */
    public TopUniversityDetailInfo selectTopUniversityDetailInfoById(@Param("school_id") String school_id);

    /**
     * 查询高校详情
     *
     * @param school_id
     * @return
     */
    public TopUniversityDetailMoreInfo selectTopUniversityDetailMoreInfoById(@Param("school_id") String school_id);

}
