package com.crazyandcoder.top.university.mvp.model.school;

import android.app.Application;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.top.university.api.SchoolApiService;
import com.crazyandcoder.top.university.bean.req.SchoolListReq;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.google.gson.Gson;

import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.mvp.CrazyBaseModel;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import com.crazyandcoder.top.university.mvp.contract.school.SchoolListContract;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class SchoolListModel extends CrazyBaseModel implements SchoolListContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SchoolListModel(IRepositoryManager repositoryManager) {
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
        return mRepositoryManager.obtainRetrofitService(SchoolApiService.class).getSchoolList(
                schoolListReq.getF211(),
                schoolListReq.getF985(),
                schoolListReq.getKeyword(),
                schoolListReq.getProvinceId(),
                schoolListReq.getType(),
                schoolListReq.getSchoolType(),
                schoolListReq.getPageNo(),
                schoolListReq.getPageSize());
    }
}