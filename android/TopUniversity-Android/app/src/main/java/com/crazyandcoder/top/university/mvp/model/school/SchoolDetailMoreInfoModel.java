package com.crazyandcoder.top.university.mvp.model.school;

import android.app.Application;

import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.top.university.api.SchoolApiService;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfoResp;
import com.google.gson.Gson;

import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.mvp.CrazyBaseModel;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailMoreInfoContract;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class SchoolDetailMoreInfoModel extends CrazyBaseModel implements SchoolDetailMoreInfoContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SchoolDetailMoreInfoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResult<SchoolMoreInfoResp>> getSchoolDetailMoreInfo(String schoolId) {
        return mRepositoryManager.obtainRetrofitService(SchoolApiService.class).getSchoolDetailMoreInfo(schoolId);
    }
}