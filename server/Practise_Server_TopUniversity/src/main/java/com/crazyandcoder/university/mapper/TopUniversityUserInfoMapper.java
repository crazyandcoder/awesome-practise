package com.crazyandcoder.university.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crazyandcoder.university.entity.TopUniversityUser;
import com.crazyandcoder.university.model.req.TopUniversityUserRegisterReq;
import com.crazyandcoder.university.model.req.TopUniversityUserUpdatePwdReq;
import com.crazyandcoder.university.model.req.TopUniversityUserUpdateReq;
import org.apache.ibatis.annotations.Param;

public interface TopUniversityUserInfoMapper extends BaseMapper<TopUniversityUser> {

    /**
     * 注册时需要先查询是否存在该用户
     *
     * @param name
     * @return
     */
    public TopUniversityUser selectUserByName(@Param("userName") String name);

    /**
     * 插入一条用户信息数据
     *
     * @param registerReq
     * @return
     */
    int addUserByName(TopUniversityUserRegisterReq registerReq);

    /**
     * 根据用户名和密码查询（登录使用）
     *
     * @param userName
     * @param userPassword
     * @return
     */
    TopUniversityUser selectUserByNameAndPassword(@Param("userName") String userName, @Param("userPassword") String userPassword);

    /**
     * 根据用户id进行查询
     *
     * @param userId
     * @return
     */
    TopUniversityUser selectUserByUserId(@Param("userId") String userId);

    /**
     * 更新用户信息
     *
     * @param req
     * @return
     */
    int updateUserInfo(TopUniversityUserUpdateReq req);


    /**
     * 修改密码
     *
     * @param req
     * @return
     */
    int updateUserPwd(TopUniversityUserUpdatePwdReq req);


    /**
     * 根据用户id和密码查询是否存在相应的用户，修改密码使用
     *
     * @param userId
     * @return
     */
    TopUniversityUser selectUserByUserIdAndPwd(@Param("userId") String userId, @Param("userPassword") String userPassword);

}
