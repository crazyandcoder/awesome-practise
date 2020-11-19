package com.crazyandcoder.university.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopUniversityUser extends BaseInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户登录密码MD5加密后
     */
    private String userPassword;

    /**
     * 原始密码
     */
    private String userNormalPassword;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户性别
     * 0：未知
     * 1：男
     * 2：女
     */
    private Integer userGender;

    /**
     * 用户年龄
     */
    private Integer userAge;

    /**
     * 用户地址
     */
    private String userAddress;

    /**
     * 用户所在城市
     */
    private String userCity;

    /**
     * 用户简介
     */
    private String userIntroduction;
}
