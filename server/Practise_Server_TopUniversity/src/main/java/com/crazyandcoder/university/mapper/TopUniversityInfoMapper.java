package com.crazyandcoder.university.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface TopUniversityInfoMapper extends BaseMapper<TopUniversityInfo> {


    /**
     * 分页查询高校列表
     *
     * @param topUniversityName
     * @param page
     * @return
     */
    public List<TopUniversityInfo> selectTopUniversitiesPage(@Param("topUniversityName") String topUniversityName, Page<TopUniversityInfo> page);


}
