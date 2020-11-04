package com.crazyandcoder.top.university.mvp.model.profession;

import android.app.Application;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.top.university.api.ProfessionApiService;
import com.crazyandcoder.top.university.api.SchoolApiService;
import com.crazyandcoder.top.university.bean.req.ProfessionHotListReq;
import com.crazyandcoder.top.university.bean.resp.ProfessionDetailInfoResp;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.google.gson.Gson;

import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.mvp.CrazyBaseModel;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionDetailInfoContract;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class ProfessionDetailInfoModel extends CrazyBaseModel implements ProfessionDetailInfoContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ProfessionDetailInfoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BasePageResult<ProfessionHotListResp>> getProfessionHotList(ProfessionHotListReq professionHotListReq) {
        return null;
    }

    @Override
    public Observable<BaseResult<ProfessionDetailInfoResp>> getProfessionDetailInfo(String professionId) {
        return mRepositoryManager.obtainRetrofitService(ProfessionApiService.class).getProfessionDetailInfo(professionId);
    }
}