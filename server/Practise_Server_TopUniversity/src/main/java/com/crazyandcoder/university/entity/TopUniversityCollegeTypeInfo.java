package com.crazyandcoder.university.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 省份选择
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopUniversityCollegeTypeInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String collegeTypeId;
    private String collegeTypeName;
}
