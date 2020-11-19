package com.crazyandcoder.university.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyandcoder.university.entity.TopUniversityCollegeTypeInfo;
import com.crazyandcoder.university.entity.TopUniversityProvinceInfo;
import com.crazyandcoder.university.entity.TopUniversitySchoolTypeInfo;
import com.crazyandcoder.university.entity.TopUniversitySelectConfigInfo;
import com.crazyandcoder.university.mapper.TopUniversityInfoSelectConfigMapper;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.service.ITopUniversityInfoSelectConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TopUnivesitySelectConfigServiceImpl extends ServiceImpl<TopUniversityInfoSelectConfigMapper, TopUniversitySelectConfigInfo> implements ITopUniversityInfoSelectConfigService {
    private static final Logger logger = LoggerFactory.getLogger(TopUnivesitySelectConfigServiceImpl.class);

    @Autowired
    TopUniversityInfoSelectConfigMapper topUniversityInfoSelectConfigMapper;


    @Override
    public Response<TopUniversitySelectConfigInfo> getSelectConfigs() {

        //所有省份
        ArrayList<TopUniversityProvinceInfo> topUniversityProvinceInfoList = topUniversityInfoSelectConfigMapper.getProvinceList();
        ArrayList<TopUniversityCollegeTypeInfo> topUniversityCollegesTypeInfoList = topUniversityInfoSelectConfigMapper.getCollegeTypeList();
        ArrayList<TopUniversitySchoolTypeInfo> topUniversitySchoolTypeInfoList = topUniversityInfoSelectConfigMapper.getSchoolTypeList();

        TopUniversitySelectConfigInfo topUniversitySelectConfigInfo = new TopUniversitySelectConfigInfo();
        if (topUniversityProvinceInfoList == null) {
            logger.info("没有查询到省份数据");
        }

        if (topUniversityCollegesTypeInfoList == null) {
            logger.info("没有查询到院校类型数据");
        }

        if (topUniversitySchoolTypeInfoList == null) {
            logger.info("没有查询到办学类型数据");
        }

        if (topUniversityProvinceInfoList == null &&
                topUniversityCollegesTypeInfoList == null &&
                topUniversitySchoolTypeInfoList == null) {
            return Response.failResult("无高校筛选条件配置数据", null);
        } else {
            topUniversitySelectConfigInfo.setCollegesTypes(topUniversityCollegesTypeInfoList);
            topUniversitySelectConfigInfo.setProvinceInfos(topUniversityProvinceInfoList);
            topUniversitySelectConfigInfo.setSchoolTypes(topUniversitySchoolTypeInfoList);
            return Response.successResult("高校筛选条件配置查询成功", topUniversitySelectConfigInfo);
        }
    }
}
