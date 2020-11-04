package com.crazyandcoder.top.university.mvp.model.main;

import android.app.Application;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.top.university.api.ProfessionApiService;
import com.crazyandcoder.top.university.api.SchoolApiService;
import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.req.ProfessionHotListReq;
import com.crazyandcoder.top.university.bean.req.SchoolHotListReq;
import com.crazyandcoder.top.university.bean.req.SchoolListReq;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.google.gson.Gson;

import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.mvp.CrazyBaseModel;

import com.crazyandcoder.android.lib.base.di.scope.FragmentScope;

import javax.inject.Inject;

import com.crazyandcoder.top.university.mvp.contract.main.HomeContract;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@FragmentScope
public class HomeModel extends CrazyBaseModel implements HomeContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public HomeModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BasePageResult<TopSchoolResp>> getSchoolList(SchoolListReq schoolListReq) {
        return mRepositoryManager.obtainRetrofitService(SchoolApiService.class).getSchoolList(schoolListReq.getF211(),
                schoolListReq.getF985(),
                schoolListReq.getKeyword(),
                schoolListReq.getProvinceId(),
                schoolListReq.getType(),
                schoolListReq.getSchoolType(),
                schoolListReq.getPageNo(),
                schoolListReq.getPageSize());
    }

    @Override
    public Observable<BasePageResult<ProfessionHotListResp>> getProfessionHotList(ProfessionHotListReq professionHotListReq) {
        return mRepositoryManager.obtainRetrofitService(ProfessionApiService.class).getProfessionHotList(
                professionHotListReq.getLevel1(),
                professionHotListReq.getPageNo(),
                professionHotListReq.getPageSize());
    }

    @Override
    public Observable<BasePageResult<TopSchoolResp>> getSchoolHotList(SchoolHotListReq schoolListReq) {
        return mRepositoryManager.obtainRetrofitService(SchoolApiService.class).getSchoolHotList(
                schoolListReq.getProvinceId(),
                schoolListReq.getType(),
                schoolListReq.getSchoolType(),
                schoolListReq.getPageNo(),
                schoolListReq.getPageSize());
    }
}