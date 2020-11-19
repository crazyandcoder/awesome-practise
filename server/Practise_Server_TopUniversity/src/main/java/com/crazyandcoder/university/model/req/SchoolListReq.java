package com.crazyandcoder.university.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="高校列表",description="查询高校列表")
public class SchoolListReq implements Serializable {
    private static final long serialVersionUID = 348862120085650012L;

    @ApiModelProperty(value = "高校名称",required = false)
    private String keyword;

    @ApiModelProperty(value = "分页，当前页码",required = true)
    private String pageNo;

    @ApiModelProperty(value = "分页，每页大小",required = true)
    private String pageSize;

    @ApiModelProperty(value = "是否是211",required = false)
    private String f211;

    @ApiModelProperty(value = "是否是985",required = false)
    private String f985;

    @ApiModelProperty(value = "省份id",required = false)
    private String province_id;

    @ApiModelProperty(value = "高校列表",required = false)
    private String school_type;

    @ApiModelProperty(value = "高校类型",required = false)
    private String type;
}
