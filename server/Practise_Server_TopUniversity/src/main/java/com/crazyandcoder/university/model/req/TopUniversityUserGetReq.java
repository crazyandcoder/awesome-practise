package com.crazyandcoder.university.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="查询用户",description="查询用户")
public class TopUniversityUserGetReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id",required = true)
    private String userId;

    @ApiModelProperty(value = "用户名称",required = true)
    private String userName;
}
