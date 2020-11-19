package com.crazyandcoder.university.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="高校详情",description="高校详情")
public class SchoolDetailReq implements Serializable {
    private static final long serialVersionUID = 348862120085650012L;

    @ApiModelProperty(value = "高校id",required = true)
    private String schoolId;
}
