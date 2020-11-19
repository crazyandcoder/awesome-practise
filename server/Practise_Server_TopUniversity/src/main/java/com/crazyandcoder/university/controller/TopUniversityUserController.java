package com.crazyandcoder.university.controller;

import com.crazyandcoder.university.entity.TopUniversityUser;
import com.crazyandcoder.university.model.Response;
import com.crazyandcoder.university.model.req.*;
import com.crazyandcoder.university.service.ITopUniversityUserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/top-university-info/user")
public class TopUniversityUserController {

    private static final Logger logger = LoggerFactory.getLogger(TopUniversityUserController.class);


    @Autowired
    ITopUniversityUserService treeHoleUserInfoService;

    /**
     * 用户注册
     *
     * @param registerReq
     * @return
     */
    @PostMapping("/register")
    public Response registerUserByName(@RequestBody @Validated TopUniversityUserRegisterReq registerReq) {
        return treeHoleUserInfoService.addUserbyName(registerReq);
    }


    /**
     * 用户登录
     *
     * @return
     */
    @GetMapping("/login")
    public Response<TopUniversityUser> loginByNameAndPassword(String userName,String userPassword) {
        return treeHoleUserInfoService.selectUserByNameAndPassword(userName,userPassword);
    }


    /**
     * 查询用户信息
     *
     * @param req
     * @return
     */
    @PostMapping("/getUserInfoByUserName")
    public Response<TopUniversityUser> getUserInfoByUserName(@RequestBody @Validated TopUniversityUserGetReq req) {
        return treeHoleUserInfoService.selectUserByUserName(req);
    }


    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserInfoByUserId")
    public Response<TopUniversityUser> getUserInfoByUserId(String userId ) {
        return treeHoleUserInfoService.getUserInfoByUserId(userId);
    }


    /**
     * 更新用户信息
     *
     * @param req
     * @return
     */
    @PostMapping("/updateUserInfo")
    public Response<TopUniversityUser> updateUserInfo(@RequestBody @Validated TopUniversityUserUpdateReq req) {
        return treeHoleUserInfoService.updateUserInfo(req);
    }



    /**
     * 更新用户信息
     *
     * @param req
     * @return
     */
    @PostMapping("/updateUserPwd")
    public Response<TopUniversityUser> updateUserPwd(@RequestBody @Validated TopUniversityUserUpdatePwdReq req) {
        return treeHoleUserInfoService.updateUserPwd(req);
    }


}
