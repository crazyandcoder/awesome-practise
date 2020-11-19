package com.crazyandcoder.university.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityDetailMoreInfo;
import com.crazyandcoder.university.entity.TopUniversityListInfo;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.service.ITopUniversityInfoService;
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
@Api(tags = "全国高校接口")
@RestController
@RequestMapping("/top-university-info/school")
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
    @GetMapping("/detail")
    public Response<TopUniversityDetailInfo> getUniversityInfoById(String school_id) {
        if (StringUtils.isBlank(school_id)) {
            logger.error("获取高校详情，schoolId不能为空'");
            return Response.failResult("高校Id不能为空");
        }
        return topUniversityInfoService.selectTopUniversityDetailInfoById(school_id);
    }


    /**
     * 通过schoolId来获取高校详情
     *
     * @param school_id
     * @return
     */
    @GetMapping("/detail/more")
    public Response<TopUniversityDetailMoreInfo> getUniversityMoreInfoById(String school_id) {
        if (StringUtils.isBlank(school_id)) {
            logger.error("获取高校详情，schoolId不能为空'");
            return Response.failResult("高校Id不能为空");
        }
        return topUniversityInfoService.selectTopUniversityDetailMoreInfoById(school_id);
    }


    /**
     * 分页查询高校列表
     *
     * @param topUniversityName 支持根据高校名称模糊搜索
     * @param pageNo            分页参数
     * @param pageSize          分页参数
     * @return
     */
    @GetMapping("/list")
    public Response<Page<TopUniversityListInfo>> getSchoolList(String f211, String f985, String keyword, String provinceId, String type, String schoolType, String pageNo, String pageSize) {
        Page<TopUniversityListInfo> page = new Page<>();
        if (StringUtils.isNotBlank(pageNo)) {
            page.setCurrent(Long.parseLong(pageNo));
        }
        if (StringUtils.isNotBlank(pageSize)) {
            page.setSize(Long.parseLong(pageSize));
        }
        return topUniversityInfoService.getUserPage(f211, f985, keyword, provinceId, type, schoolType, page);
    }


    /**
     * 分页查询高校列表
     *
     * @param topUniversityName 支持根据高校名称模糊搜索
     * @param pageNo            分页参数
     * @param pageSize          分页参数
     * @return
     */
    @GetMapping("/hotlist")
    public Response<Page<TopUniversityListInfo>> getSchoolHotList(String provinceId, String type, String schoolType, String pageNo, String pageSize) {
        Page<TopUniversityListInfo> page = new Page<>();
        if (StringUtils.isNotBlank(pageNo)) {
            page.setCurrent(Long.parseLong(pageNo));
        }
        if (StringUtils.isNotBlank(pageSize)) {
            page.setSize(Long.parseLong(pageSize));
        }
        return topUniversityInfoService.getSchoolHotList(provinceId, type, schoolType, page);
    }


}
