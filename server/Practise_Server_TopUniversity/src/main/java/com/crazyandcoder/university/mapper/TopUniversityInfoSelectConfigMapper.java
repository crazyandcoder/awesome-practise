package com.crazyandcoder.university.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crazyandcoder.university.entity.TopUniversityCollegeTypeInfo;
import com.crazyandcoder.university.entity.TopUniversityProvinceInfo;
import com.crazyandcoder.university.entity.TopUniversitySchoolTypeInfo;
import com.crazyandcoder.university.entity.TopUniversitySelectConfigInfo;

import java.util.ArrayList;

/**
 * 高校筛选条件
 */
public interface TopUniversityInfoSelectConfigMapper extends BaseMapper<TopUniversitySelectConfigInfo> {

    /**
     * 获取所有省份
     *
     * @return
     */
    ArrayList<TopUniversityProvinceInfo> getProvinceList();

    /**
     * 获取所有院校类型
     *
     * @return
     */
    ArrayList<TopUniversityCollegeTypeInfo> getCollegeTypeList();

    /**
     * 获取所有办学类型
     *
     * @return
     */
    ArrayList<TopUniversitySchoolTypeInfo> getSchoolTypeList();
}
