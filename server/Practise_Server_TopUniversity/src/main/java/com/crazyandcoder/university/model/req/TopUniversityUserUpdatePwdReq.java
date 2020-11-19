package com.crazyandcoder.university.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="更新用户密码信息",description="更新用户密码信息")
public class TopUniversityUserUpdatePwdReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户原始密码",required = true)
    private String userNormalPassword;

    @ApiModelProperty(value = "用户密码，加密过后",required = true)
    private String userPassword;

    @ApiModelProperty(value = "以前密码，加密过后",required = true)
    private String userPrePassword;

    @ApiModelProperty(value = "用户id",required = true)
    private String userId;

}
