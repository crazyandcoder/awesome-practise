package com.crazyandcoder.university.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyandcoder.university.entity.TopUniversityDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityListInfo;
import com.crazyandcoder.university.entity.TopUniversityProfessionDetailInfo;
import com.crazyandcoder.university.entity.TopUniversityProfessionListInfo;
import com.crazyandcoder.university.mapper.TopUniversityProfessionMapper;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.service.ITopUniversityProfessionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopUniversityProfessionServiceImpl extends ServiceImpl<TopUniversityProfessionMapper, TopUniversityProfessionListInfo> implements ITopUniversityProfessionService {
    private static final Logger logger = LoggerFactory.getLogger(TopUniversityProfessionServiceImpl.class);

    @Autowired
    TopUniversityProfessionMapper topUniversityProfessionMapper;

    /**
     * 根据专业id查询专业详情
     *
     * @param profession_id
     * @return
     */
    @Override
    public Response<TopUniversityProfessionDetailInfo> selectTopUniversityProfessionDetailInfoById(String profession_id) {
        if (StringUtils.isBlank(profession_id)) {
            logger.error("查询专业详情信息时，specialId不能为空'");
            return Response.failResult("专业Id不能为空");
        }
        TopUniversityProfessionDetailInfo topUniversityProfessionDetailInfo = topUniversityProfessionMapper.selectTopUniversityProfessionDetailInfoById(profession_id);
        if (topUniversityProfessionDetailInfo != null) {
            logger.info("查询高校详专业情成功，专业Id：{}", profession_id);
            return Response.successResult("查询高校专业详情成功", topUniversityProfessionDetailInfo);
        } else {
            logger.info("查询高校专业详情失败，专业Id：{}", profession_id);
            return Response.failResult("查询高校专业详情失败", null);
        }
    }

    /**
     * 查询高校专业
     *
     * @param keyword 支持根据专业名称模糊搜索
     * @param level1
     * @param level2
     * @param page    分页参数
     * @return
     */
    @Override
    public Response<Page<TopUniversityProfessionListInfo>> getProfessionListPage(String keyword, String level1, String level3, Page<TopUniversityProfessionListInfo> page) {
        List<TopUniversityProfessionListInfo> list = topUniversityProfessionMapper.selectTopUniversitiesProfessionListPage(
                keyword,
                level1,
                level3,
                page);
        page.setRecords(list);
        return Response.successResult("查询成功", page);
    }

    /**
     * 查询热门专业
     *
     * @param level1
     * @param sortType
     * @param page
     * @return
     */
    @Override
    public Response<Page<TopUniversityProfessionListInfo>> getSchoolProfessionHotList(String level1,  Page<TopUniversityProfessionListInfo> page) {
        List<TopUniversityProfessionListInfo> list = topUniversityProfessionMapper.selectTopUniversitiesProfessionHotListPage(level1, page);
        page.setRecords(list);
        return Response.successResult("查询成功", page);
    }
}
