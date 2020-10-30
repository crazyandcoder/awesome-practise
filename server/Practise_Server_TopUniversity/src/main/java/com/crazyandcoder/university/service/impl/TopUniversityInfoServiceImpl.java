package com.crazyandcoder.university.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crazyandcoder.university.entity.TopUniversityInfo;
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
public class TopUniversityInfoServiceImpl extends ServiceImpl<TopUniversityInfoMapper, TopUniversityInfo> implements ITopUniversityInfoService {

    private static final Logger logger = LoggerFactory.getLogger(TopUniversityInfoServiceImpl.class);

    @Autowired
    TopUniversityInfoMapper topUniversityInfoMapper;


    @Override
    public Response<TopUniversityInfo> getUniversityInfoById(String schoolId) {
        if (StringUtils.isBlank(schoolId)) {
            logger.error("查询高校详情信息时，schoolId不能为空'");
            return Response.failResult("高校Id不能为空");
        }
        TopUniversityInfo topUniversityInfo = topUniversityInfoMapper.selectById(schoolId);
        if (topUniversityInfo != null) {
            logger.info("查询高校详情成功，高校Id：{}", schoolId);
            return Response.successResult("查询高校详情成功", topUniversityInfo);
        } else {
            logger.info("查询高校详情失败，高校Id：{}", schoolId);
            return Response.failResult("查询高校详情失败", null);
        }
    }

    @Override
    public Response<Page<TopUniversityInfo>> getUserPage(String topUniversityName, Page<TopUniversityInfo> page) {
        List<TopUniversityInfo> list = topUniversityInfoMapper.selectTopUniversitiesPage(topUniversityName, page);
        page.setRecords(list);
        return Response.successResult("查询成功", page);
    }
}
