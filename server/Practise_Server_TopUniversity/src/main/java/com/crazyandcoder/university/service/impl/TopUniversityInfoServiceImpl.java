package com.crazyandcoder.university.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityDetailMoreInfo;
import com.crazyandcoder.university.entity.TopUniversityListInfo;
import com.crazyandcoder.university.mapper.TopUniversityInfoMapper;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.service.ITopUniversityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liji
 * @since 2020-07-14
 */
@Service
public class TopUniversityInfoServiceImpl extends ServiceImpl<TopUniversityInfoMapper, TopUniversityListInfo> implements ITopUniversityInfoService {

    private static final Logger logger = LoggerFactory.getLogger(TopUniversityInfoServiceImpl.class);

    @Autowired
    TopUniversityInfoMapper topUniversityInfoMapper;


    @Override
    public Response<TopUniversityDetailMoreInfo> selectTopUniversityDetailMoreInfoById(String schoolId) {
        if (StringUtils.isBlank(schoolId)) {
            logger.error("查询高校详情信息时，schoolId不能为空'");
            return Response.failResult("高校Id不能为空");
        }
        TopUniversityDetailMoreInfo topUniversityDetailInfo = topUniversityInfoMapper.selectTopUniversityDetailMoreInfoById(schoolId);
        if (topUniversityDetailInfo != null) {
            logger.info("查询高校详情成功，高校Id：{}", schoolId);
            return Response.successResult("查询高校详情成功", topUniversityDetailInfo);
        } else {
            logger.info("查询高校详情失败，高校Id：{}", schoolId);
            return Response.failResult("查询高校详情失败", null);
        }
    }

    @Override
    public Response<TopUniversityDetailInfo> selectTopUniversityDetailInfoById(String schoolId) {
        if (StringUtils.isBlank(schoolId)) {
            logger.error("查询高校详情信息时，schoolId不能为空'");
            return Response.failResult("高校Id不能为空");
        }
        TopUniversityDetailInfo topUniversityDetailInfo = topUniversityInfoMapper.selectTopUniversityDetailInfoById(schoolId);
        if (topUniversityDetailInfo != null) {
            logger.info("查询高校详情成功，高校Id：{}", schoolId);
            return Response.successResult("查询高校详情成功", topUniversityDetailInfo);
        } else {
            logger.info("查询高校详情失败，高校Id：{}", schoolId);
            return Response.failResult("查询高校详情失败", null);
        }
    }

    @Override
    public Response<Page<TopUniversityListInfo>> getUserPage(String f211, String f985, String keyword, String provinceId, String type, String schoolType, Page<TopUniversityListInfo> page) {
        List<TopUniversityListInfo> list = topUniversityInfoMapper.selectTopUniversitiesPage(
                f211,
                f985,
                keyword,
                provinceId,
                type,
                schoolType,
                page);
        page.setRecords(list);
        return Response.successResult("查询成功", page);
    }

    @Override
    public Response<Page<TopUniversityListInfo>> getSchoolHotList(String provinceId, String type, String schoolType, Page<TopUniversityListInfo> page) {
        List<TopUniversityListInfo> list = topUniversityInfoMapper.selectTopUniversitiesHotPage(
                provinceId,
                type,
                schoolType,
                page);
        page.setRecords(list);
        return Response.successResult("查询成功", page);
    }
}
