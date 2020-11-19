package com.crazyandcoder.university.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityListInfo;
import com.crazyandcoder.university.entity.TopUniversityProfessionDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityProfessionListInfo;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.service.ITopUniversityInfoService;
import com.crazyandcoder.university.service.ITopUniversityProfessionService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liji
 * @since 2020-07-14
 */
@Api(tags = "高校专业接口")
@RestController
@RequestMapping("/top-university-info/profession")
public class TopUniversityProfessionController {

    private static final Logger logger = LoggerFactory.getLogger(TopUniversityProfessionController.class);

    @Autowired
    ITopUniversityProfessionService topUniversityProfessionService;

    /**
     * 通过special_id来获取高校专业详情
     *
     * @param professionId
     * @return
     */
    @GetMapping("/detail")
    public Response<TopUniversityProfessionDetailInfo> getUniversityProfessionInfoById(String professionId) {
        if (StringUtils.isBlank(professionId)) {
            logger.error("获取高校专业详情，special_id不能为空'");
            return Response.failResult("高校专业Id不能为空");
        }
        return topUniversityProfessionService.selectTopUniversityProfessionDetailInfoById(professionId);
    }


    /**
     * 分页查询高校专业列表
     *
     * @param keyword  支持根据高校名称模糊搜索
     * @param pageNo   分页参数
     * @param pageSize 分页参数
     * @return
     */
    @GetMapping("/list")
    public Response<Page<TopUniversityProfessionListInfo>> getSchoolProfessionList(String keyword, String level1, String level3, String pageNo, String pageSize) {
        Page<TopUniversityProfessionListInfo> page = new Page<>();
        if (StringUtils.isNotBlank(pageNo)) {
            page.setCurrent(Long.parseLong(pageNo));
        }
        if (StringUtils.isNotBlank(pageSize)) {
            page.setSize(Long.parseLong(pageSize));
        }
        return topUniversityProfessionService.getProfessionListPage(keyword, level1, level3, page);
    }


    /**
     * 分页查询高校热门专业列表
     *
     * @param pageNo   分页参数
     * @param pageSize 分页参数
     * @return
     */
    @GetMapping("/hotlist")
    public Response<Page<TopUniversityProfessionListInfo>> getSchoolProfessionHotList(String level1, String pageNo, String pageSize) {
        Page<TopUniversityProfessionListInfo> page = new Page<>();
        if (StringUtils.isNotBlank(pageNo)) {
            page.setCurrent(Long.parseLong(pageNo));
        }
        if (StringUtils.isNotBlank(pageSize)) {
            page.setSize(Long.parseLong(pageSize));
        }
        return topUniversityProfessionService.getSchoolProfessionHotList(level1, page);
    }


}
