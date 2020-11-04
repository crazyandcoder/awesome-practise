package com.crazyandcoder.top.university.mvp.model.user;

import android.app.Application;

import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.utils.downlaoder.MD5Util;
import com.crazyandcoder.top.university.api.UserApiService;
import com.crazyandcoder.top.university.bean.User;
import com.crazyandcoder.top.university.bean.req.TopUniversityUserRegisterReq;
import com.crazyandcoder.top.university.utils.MD5Utils;
import com.google.gson.Gson;

import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.mvp.CrazyBaseModel;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import com.crazyandcoder.top.university.mvp.contract.user.RegisterContract;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class RegisterModel extends CrazyBaseModel implements RegisterContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public RegisterModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResult<User>> register(String name, String pwd, String normalPwd) {
        TopUniversityUserRegisterReq registerReq=new TopUniversityUserRegisterReq();
        registerReq.setUserName(name);
        registerReq.setUserPassword(MD5Utils.encode(pwd));
        registerReq.setUserNormalPassword(normalPwd);
        return mRepositoryManager.obtainRetrofitService(UserApiService.class).register(registerReq);
    }
}