package com.crazyandcoder.university.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="用户高校收藏",description="用户高校收藏")
public class TopUniversityUserFavReq implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户id",required = true)
    private String userId;

    /**
     * 收藏的type
     * 1.大学
     * 2。专业
     */

    @ApiModelProperty(value = "收藏类型，高校还是专业",required = true)
    private Integer favType;

    @ApiModelProperty(value = "高校还是专业id",required = true)
    private String favId;

    /**
     * 收藏动作
     * true 收藏
     * false 取消收藏
     */

    @ApiModelProperty(value = "取消收藏还是添加收藏",required = true)
    private Boolean fav;
}
