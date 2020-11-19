package com.crazyandcoder.university.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="用户登录",description="用户登录")
public class TopUniversityUserLoginReq implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "登录密码",required = true)
    private String userPassword;

    @ApiModelProperty(value = "用户名称",required = true)
    private String userName;
}
