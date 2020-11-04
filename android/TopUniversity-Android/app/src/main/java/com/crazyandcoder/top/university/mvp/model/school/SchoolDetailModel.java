package com.crazyandcoder.top.university.mvp.model.school;

import android.app.Application;

import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.top.university.api.SchoolApiService;
import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.req.SchoolDetailInfoReq;
import com.google.gson.Gson;

import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.mvp.CrazyBaseModel;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailContract;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class SchoolDetailModel extends CrazyBaseModel implements SchoolDetailContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SchoolDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResult<School>> getSchoolDetail(SchoolDetailInfoReq schoolDetailInfoReq) {
        return mRepositoryManager.obtainRetrofitService(SchoolApiService.class).getSchoolDetailInfo(schoolDetailInfoReq.getSchoolId());
    }
}