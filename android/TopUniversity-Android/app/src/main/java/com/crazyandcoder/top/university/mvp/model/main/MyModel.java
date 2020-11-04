package com.crazyandcoder.top.university.mvp.model.main;

import android.app.Application;

import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.utils.AppUtils;
import com.crazyandcoder.top.university.api.CommonApiService;
import com.crazyandcoder.top.university.api.ProfessionApiService;
import com.crazyandcoder.top.university.bean.UpdateCoreSuper;
import com.google.gson.Gson;

import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.mvp.CrazyBaseModel;

import com.crazyandcoder.android.lib.base.di.scope.FragmentScope;

import javax.inject.Inject;

import com.crazyandcoder.top.university.mvp.contract.main.MyContract;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@FragmentScope
public class MyModel extends CrazyBaseModel implements MyContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public MyModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResult<UpdateCoreSuper>> checkUpdate() {
        return mRepositoryManager.obtainRetrofitService(CommonApiService.class).checkUpdate(
                "com.crazyandcoder.top.university",
                AppUtils.getVersionCode()
        );
    }
}