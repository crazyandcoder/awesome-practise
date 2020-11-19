package com.crazyandcoder.university.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="更新用户信息",description="更新用户信息")
public class TopUniversityUserUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id",required = true)
    private String userId;

    @ApiModelProperty(value = "用户昵称",required = false)
    private String userNickName;

    @ApiModelProperty(value = "用户头像",required = false)
    private String userAvatar;

    @ApiModelProperty(value = "用户地址",required = false)
    private String userAddress;

    @ApiModelProperty(value = "用户所在城市",required = false)
    private String userCity;

    @ApiModelProperty(value = "手机号码",required = false)
    private String userPhone;

    @ApiModelProperty(value = "邮箱",required = false)
    private String userEmail;

    @ApiModelProperty(value = "用户简介",required = false)
    private String userIntroduction;

    @ApiModelProperty(value = "年龄",required = false)
    private int userAge;

    @ApiModelProperty(value = "性别",required = false)
    private int userGender;

}
