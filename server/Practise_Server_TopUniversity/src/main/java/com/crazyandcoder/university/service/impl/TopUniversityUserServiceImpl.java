package com.crazyandcoder.university.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crazyandcoder.university.entity.TopUniversityUser;
import com.crazyandcoder.university.mapper.TopUniversityUserInfoMapper;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.model.req.*;
import com.crazyandcoder.university.service.ITopUniversityUserService;
import com.crazyandcoder.university.util.RecordNoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopUniversityUserServiceImpl extends ServiceImpl<TopUniversityUserInfoMapper, TopUniversityUser> implements ITopUniversityUserService {


    private static final Logger logger = LoggerFactory.getLogger(TopUniversityUserServiceImpl.class);

    @Autowired
    TopUniversityUserInfoMapper topUniversityUserInfoMapper;

    /**
     * 注册
     *
     * @param registerReq
     * @return
     */
    @Override
    public Response addUserbyName(TopUniversityUserRegisterReq registerReq) {
        if (StringUtils.isBlank(registerReq.getUserName()) || StringUtils.isBlank(registerReq.getUserPassword()) || StringUtils.isBlank(registerReq.getUserNormalPassword())) {
            logger.error("用户名或密码不能为空");
            return Response.failResult("用户名或密码为空");
        }
        TopUniversityUser userInfo = topUniversityUserInfoMapper.selectUserByName(registerReq.getUserName());
        if (userInfo != null && !StringUtils.isBlank(userInfo.getUserName())) {
            logger.error("用户名已存在");
            return Response.failResult("用户名已存在");
        } else {
            registerReq.setUserNickName("匿名");
            registerReq.setUserGender(1);
            registerReq.setUserAge(0);
            registerReq.setUserAddress("未知");
            registerReq.setUserPhone("");
            registerReq.setUserAvatar("");
            registerReq.setUserIntroduction("这个人什么都没有留下");
            registerReq.setUserCity("");
            registerReq.setUserEmail("");
            registerReq.setUserId("" + RecordNoUtils.getInstance().get("u") + System.currentTimeMillis());
            int result = topUniversityUserInfoMapper.addUserByName(registerReq);
            if (result == 1) {
                logger.info("注册成功");
                return Response.successResult("注册成功");
            } else {
                logger.info("注册失败");
                return Response.failResult("注册失败");
            }
        }
    }

    /**
     * 登录
     *
     * @return
     */
    @Override
    public Response<TopUniversityUser> selectUserByNameAndPassword(String userName,String userPassword) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(userPassword)) {
            logger.error("用户名或密码不能为空");
            return Response.failResult("用户名或密码为空");
        }
        TopUniversityUser user = topUniversityUserInfoMapper.selectUserByName(userName);
        if (user == null) {
            logger.error("用户不存在");
            return Response.failResult("用户不存在");
        }

        TopUniversityUser userInfo = topUniversityUserInfoMapper.selectUserByNameAndPassword(userName, userPassword);
        if (userInfo == null || StringUtils.isBlank(userInfo.getUserId())) {
            logger.error("用户名或密码错误");
            return Response.failResult("用户名或密码错误");
        } else {
            logger.info("登录成功");
            return Response.successResult("登录成功", userInfo);
        }

    }

    @Override
    public Response<TopUniversityUser> selectUserByUserName(TopUniversityUserGetReq req) {
        if (StringUtils.isBlank(req.getUserName())) {
            logger.error("用户名为空");
            return Response.failResult("用户名为空");
        }
        TopUniversityUser user = topUniversityUserInfoMapper.selectUserByName(req.getUserName());
        if (user == null) {
            logger.error("用户名不存在");
            return Response.failResult("用户名不存在");
        } else {
            logger.info("用户信息查询成功" + user.toString());
            return Response.successResult("用户信息查询成功", user);
        }
    }

    @Override
    public Response<TopUniversityUser> getUserInfoByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            logger.error("用户Id为空");
            return Response.failResult("用户Id为空");
        }
        TopUniversityUser user = topUniversityUserInfoMapper.selectUserByUserId(userId);
        if (user == null) {
            logger.error("用户名不存在");
            return Response.failResult("用户名不存在");
        } else {
            logger.info("用户信息查询成功" + user.toString());
            return Response.successResult("用户信息查询成功", user);
        }
    }

    @Override
    public Response<TopUniversityUser> updateUserInfo(TopUniversityUserUpdateReq req) {
        if (StringUtils.isBlank(req.getUserId())) {
            logger.error("用户Id为空");
            return Response.failResult("用户Id为空");
        }

        int update = topUniversityUserInfoMapper.updateUserInfo(req);
        if (update == 1) {
            logger.info("用户信息更新成功");
            return Response.successResult("用户信息更新成功", null);
        } else {
            logger.error("用户信息更新失败");
            return Response.failResult("用户信息更新失败");
        }

    }

    @Override
    public Response<TopUniversityUser> updateUserPwd(TopUniversityUserUpdatePwdReq req) {
        if (StringUtils.isBlank(req.getUserId())) {
            logger.error("用户Id为空");
            return Response.failResult("用户Id为空");
        }

        TopUniversityUser user = topUniversityUserInfoMapper.selectUserByUserIdAndPwd(req.getUserId(), req.getUserPrePassword());
        if (user == null) {
            logger.error("原始密码错误");
            return Response.failResult("原始密码错误");
        }

        int update = topUniversityUserInfoMapper.updateUserPwd(req);
        if (update == 1) {
            logger.info("密码修改成功");
            return Response.successResult("密码修改成功", null);
        } else {
            logger.error("密码修改失败");
            return Response.failResult("密码修改失败");
        }

    }
}
