package com.crazyandcoder.top.university.mvp.model.profession;

import android.app.Application;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.top.university.api.ProfessionApiService;
import com.crazyandcoder.top.university.api.SchoolApiService;
import com.crazyandcoder.top.university.bean.req.ProfessionListReq;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.google.gson.Gson;

import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.mvp.CrazyBaseModel;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionListContract;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class ProfessionListModel extends CrazyBaseModel implements ProfessionListContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ProfessionListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BasePageResult<ProfessionHotListResp>> getProfessionList(ProfessionListReq professionListReq) {
        return mRepositoryManager.obtainRetrofitService(ProfessionApiService.class).getProfessionList(
                professionListReq.getKeyword(),
                professionListReq.getLevel1(),
                professionListReq.getPageNo(),
                professionListReq.getPageSize());
    }
}