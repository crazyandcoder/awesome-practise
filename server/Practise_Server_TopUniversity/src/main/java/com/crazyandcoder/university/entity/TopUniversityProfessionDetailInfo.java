package com.crazyandcoder.university.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopUniversityProfessionDetailInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String code;
    private String continue_exp;
    private String degree;
    private String do_what;
    private String e_status;
    private String heat;
    private String impress;
    private String is_what;
    private String job;
    private String jobdetail;
    private String jobrate;
    private String learn_what;
    private String level1;
    private String level1_name;
    private String level2;
    private String level3;
    private String limit_year;
    private String name;
    private String rate;
    private String sel_adv;
    private String status;
    private String type;
    private String type_detail;

}

