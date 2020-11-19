package com.crazyandcoder.university.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="用户注册",description="用户注册")
public class TopUniversityUserRegisterReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id，自动生成",required = false)
    private String userId;

    @ApiModelProperty(value = "用户密码，加密过后的",required = true)
    private String userPassword;

    @ApiModelProperty(value = "用户原始密码",required = true)
    private String userNormalPassword;

    @ApiModelProperty(value = "用户昵称",required = false)
    private String userNickName;

    @ApiModelProperty(value = "用户名称",required = true)
    private String userName;

    @ApiModelProperty(value = "用户头像",required = false)
    private String userAvatar;

    @ApiModelProperty(value = "用户地址",required = false)
    private String userAddress;

    @ApiModelProperty(value = "用户所在城市",required = false)
    private String userCity;

    @ApiModelProperty(value = "手机号码",required = false)
    private String userPhone;

    @ApiModelProperty(value = "电子邮箱",required = false)
    private String userEmail;

    @ApiModelProperty(value = "用户简介",required = false)
    private String userIntroduction;

    @ApiModelProperty(value = "年龄",required = false)
    private int userAge;

    @ApiModelProperty(value = "性别",required = false)
    private int userGender;

}
