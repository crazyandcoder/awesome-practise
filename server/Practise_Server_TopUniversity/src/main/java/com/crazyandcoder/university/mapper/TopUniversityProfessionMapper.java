package com.crazyandcoder.university.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityListInfo;
import com.crazyandcoder.university.entity.TopUniversityProfessionDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityProfessionListInfo;
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
public interface TopUniversityProfessionMapper extends BaseMapper<TopUniversityProfessionListInfo> {


    /**
     * 分页查询高校专业列表
     *
     * @param keyword
     * @param level1
     * @param level2
     * @param page
     * @return
     */
    public List<TopUniversityProfessionListInfo> selectTopUniversitiesProfessionListPage(@Param("keyword") String keyword,
                                                                                         @Param("level1") String level1,
                                                                                         @Param("level3") String level3,
                                                                                         Page<TopUniversityProfessionListInfo> page);


    /**
     * 分页查询高校热门专业列表
     *
     */
    public List<TopUniversityProfessionListInfo> selectTopUniversitiesProfessionHotListPage(
            @Param("level1") String level1,
            Page<TopUniversityProfessionListInfo> page);

    /**
     * 查询高校专业详情
     *
     * @return
     */
    public TopUniversityProfessionDetailInfo selectTopUniversityProfessionDetailInfoById(@Param("profession_id") String profession_id);

}
