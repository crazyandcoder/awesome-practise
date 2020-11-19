package com.crazyandcoder.university.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopUniversityProfessionListInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String professionId;
    private String degree;
    private String level1;
    private String level2;
    private String level3;
    private String level1Name;
    private String level2Name;
    private String level3Name;
    private String limitYear;
    private String name;
    private String rank;
    private String rankType;
    private String rankall;
    private String spcode;
    private String specialId;
    private String viewMonth;
    private String viewTotal;
    private String viewWeek;

}
