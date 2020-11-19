package com.crazyandcoder.university.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopUniversitySelectConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 省份类型
     */
    private ArrayList<TopUniversityProvinceInfo> provinceInfos;

    /**
     * 院校类型
     */
    private ArrayList<TopUniversityCollegeTypeInfo> collegesTypes;

    /**
     * 办学类型
     */
    private ArrayList<TopUniversitySchoolTypeInfo> schoolTypes;
}
