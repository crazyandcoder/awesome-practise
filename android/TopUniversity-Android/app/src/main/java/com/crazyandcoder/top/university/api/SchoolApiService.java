package com.crazyandcoder.top.university.api;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.req.SchoolDetailInfoReq;
import com.crazyandcoder.top.university.bean.req.SchoolListReq;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfoResp;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SchoolApiService {

    /**
     * 获取学校列表
     *
     * @return
     */
    @GET("/top-university-info/school/list")
    Observable<BasePageResult<TopSchoolResp>> getSchoolList(@Query("f211") String f211,
                                                            @Query("f985") String f985,
                                                            @Query("keyword") String keyword,
                                                            @Query("provinceId") String provinceId,
                                                            @Query("type") String type,
                                                            @Query("schoolType") String schoolType,
                                                            @Query("pageNo") String pageNo,
                                                            @Query("pageSize") String pageSize);

    /**
     * 获取学校列表
     *
     * @return
     */
    @GET("/top-university-info/school/hotlist")
    Observable<BasePageResult<TopSchoolResp>> getSchoolHotList(@Query("provinceId") String provinceId,
                                                               @Query("type") String type,
                                                               @Query("schoolType") String schoolType,
                                                               @Query("pageNo") String pageNo,
                                                               @Query("pageSize") String pageSize);

    /**
     * 获取学校详情
     *
     * @param schoolId
     * @return
     */
    @GET("/top-university-info/school/detail")
    Observable<BaseResult<School>> getSchoolDetailInfo(@Query("school_id") String schoolId);


    /**
     * 获取学校详情
     *
     * @param schoolId
     * @return
     */
    @GET("/top-university-info/school/detail/more")
    Observable<BaseResult<SchoolMoreInfoResp>> getSchoolDetailMoreInfo(@Query("school_id") String schoolId);


}
