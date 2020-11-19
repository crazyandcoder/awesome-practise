package com.crazyandcoder.university.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author liji
 * @since 2020-07-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopUniversityListInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String logo;
    private String address;
    private String belong;
    private String cityId;
    private String cityName;
    private String codeEnroll;
    private String collegesLevel;
    private String department;
    private String dualClass;
    private String dualClassName;
    private String f211;
    private String f985;
    private String level;
    private String levelName;
    private String isTop;
    private String name;
    private String natureName;
    private String nature;
    private String provinceId;
    private String provinceName;
    private String publishId;
    private String schoolId;
    private String schoolType;
    private String type;
    private String typeName;


}
