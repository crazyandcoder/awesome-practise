package com.crazyandcoder.top.university.api;

import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.top.university.bean.UpdateCoreSuper;
import com.crazyandcoder.top.university.bean.User;
import com.crazyandcoder.top.university.bean.req.TopUniversityUserRegisterReq;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommonApiService {

    /**
     * @return
     */
    @GET("/crazyandcoder/update/info")
    Observable<BaseResult<UpdateCoreSuper>> checkUpdate(@Query("pkg") String pkg,
                                                        @Query("versionCode") int versionCode);


}
