package com.crazyandcoder.university.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityInfo;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.service.ITopUniversityInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RestController
@RequestMapping("/top-university-info")
public class TopUniversityInfoController {

    private static final Logger logger = LoggerFactory.getLogger(TopUniversityInfoController.class);

    @Autowired
    ITopUniversityInfoService topUniversityInfoService;

    /**
     * 通过schoolId来获取高校详情
     *
     * @param schoolId
     * @return
     */
    @PostMapping("/detail")
    public Response<TopUniversityInfo> getUniversityInfoById(String schoolId) {
        if (StringUtils.isBlank(schoolId)) {
            logger.error("获取高校详情，schoolId不能为空'");
            return Response.failResult("高校Id不能为空");
        }
        return topUniversityInfoService.getUniversityInfoById(schoolId);
    }


    /**
     * 分页查询高校列表
     *
     * @param topUniversityName   支持根据高校名称模糊搜索
     * @param pageNo   分页参数
     * @param pageSize 分页参数
     * @return
     */
    @PostMapping("/list")
    public Response<Page<TopUniversityInfo>> getUserPage(String topUniversityName, String pageNo, String pageSize) {
        Page<TopUniversityInfo> page = new Page<>();
        if (StringUtils.isNotBlank(pageNo)) {
            page.setCurrent(Long.parseLong(pageNo));
        }
        if (StringUtils.isNotBlank(pageSize)) {
            page.setSize(Long.parseLong(pageSize));
        }
        return topUniversityInfoService.getUserPage(topUniversityName, page);
    }


}
