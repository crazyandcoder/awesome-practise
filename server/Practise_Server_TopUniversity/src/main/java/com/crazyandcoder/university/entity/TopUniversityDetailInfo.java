package com.crazyandcoder.university.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopUniversityDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String schoolId;
    private String schoolNo;
    private String schoolName;
    private String schoolLogo;
    private String schoolAddress;
    private String schoolBelong;
    private String schoolCreateTime;
    private String schoolArea;
    private String schoolLibraryNum;
    private String schoolAcademicianNum;
    private String schoolDoctorNum;
    private String schoolMasterNum;
    private String schoolLabNum;
    private String schoolSubjectNum;
    private String schoolPhone;
    private String schoolPostcode;
    private String schoolEmail;
    private String schoolContent;
    private String schoolProvinceName;
    private String schoolProvinceId;
    private String schoolCityName;
    private String schoolCityId;
    private String schoolTownName;
    private String schoolLevelName;
    private String schoolLevelId;
    private String schoolTypeName;
    private String schoolType;
    private String schoolNatureName;
    private String schoolZsbSite;
    private String schoolSite;
    private String school211;
    private String school985;
    private String schoolShort;
    private String schoolImages;
}
