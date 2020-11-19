package com.crazyandcoder.university.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

//学院设置1
//学校概况2
//师资力量3
//体检标准4
//收费标准5
//就业情况6
//录取规则7
//学校领导8
//重点学科9
//招生政策10
//一流学科11
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopUniversityDetailMoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String schoolId;
    private String aboutXueXiaoGaiKuang;
    private String aboutYuanXiSheZhi;
    private String aboutShiZiLiLiang;
    private String aboutTiJianBiaoZhun;
    private String aboutShouFeiBiaoZhun;
    private String aboutJiuYeQingKuang;
    private String aboutLuQuGuiZe;
    private String aboutXueXiaoLingDao;
    private String aboutZhongDianXueKe;
    private String aboutZhaoShengZhengCe;
    private String aboutYiLiuXueKe;
}
