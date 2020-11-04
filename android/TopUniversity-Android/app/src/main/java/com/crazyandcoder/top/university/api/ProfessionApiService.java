package com.crazyandcoder.top.university.api;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.top.university.bean.resp.ProfessionDetailInfoResp;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProfessionApiService {

    /**
     * 获取专业列表
     *
     * @return
     */
    @GET("/top-university-info/profession/list")
    Observable<BasePageResult<ProfessionHotListResp>> getProfessionList(@Query("keyword") String keyword, @Query("level1") String level1,  @Query("pageNo") String pageNo, @Query("pageSize") String pageSize);

    /**
     * 获取热门专业列表
     *
     * @return
     */
    @GET("/top-university-info/profession/hotlist")
    Observable<BasePageResult<ProfessionHotListResp>> getProfessionHotList(@Query("level1") String level1, @Query("pageNo") String pageNo, @Query("pageSize") String pageSize);

    /**
     * 获取热门专业列表
     *
     * @return
     */
    @GET("/top-university-info/profession/detail")
    Observable<BaseResult<ProfessionDetailInfoResp>> getProfessionDetailInfo(@Query("professionId") String professionId);


}
