package com.crazyandcoder.top.university.api;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.User;
import com.crazyandcoder.top.university.bean.req.TopUniversityUserRegisterReq;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfoResp;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApiService {

    /**
     * @return
     */
    @GET("/top-university-info/user/login")
    Observable<BaseResult<User>> login(@Query("userName") String userName,
                                       @Query("userPassword") String userPassword);


    /**
     * @return
     */
    @POST("/top-university-info/user/register")
    Observable<BaseResult<User>> register(@Body TopUniversityUserRegisterReq registerReq);


}
