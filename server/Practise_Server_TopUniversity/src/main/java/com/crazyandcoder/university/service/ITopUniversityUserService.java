package com.crazyandcoder.university.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crazyandcoder.university.entity.TopUniversityUser;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.model.req.*;

/**
 * 用户信息
 */
public interface ITopUniversityUserService extends IService<TopUniversityUser> {


    /**
     * 注册用户
     *
     * @param registerReq
     * @return
     */
    Response addUserbyName(TopUniversityUserRegisterReq registerReq);


    /**
     * 用户登录
     *
     * @return
     */
    Response<TopUniversityUser> selectUserByNameAndPassword(String userName,String userPassword);

    /**
     * 查询用户信息
     *
     * @param req
     * @return
     */
    Response<TopUniversityUser> selectUserByUserName(TopUniversityUserGetReq req);

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    Response<TopUniversityUser> getUserInfoByUserId(String userId);

    /**
     * 更新用户信息
     *
     * @param req
     * @return
     */
    Response<TopUniversityUser> updateUserInfo(TopUniversityUserUpdateReq req);

    /**
     * 更改密码
     *
     * @param req
     * @return
     */
    Response<TopUniversityUser> updateUserPwd(TopUniversityUserUpdatePwdReq req);
}
