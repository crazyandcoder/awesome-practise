package com.crazyandcoder.top.university.mvp.presenter.user;

import android.app.Application;


import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.http.OnRequestCallback;
import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.User;
import com.crazyandcoder.top.university.bean.req.SchoolDetailInfoReq;
import com.crazyandcoder.top.university.mvp.contract.user.LoginContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class LoginPresenter extends CrazyBasePresenter<LoginContract.Model, LoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView) {
        super(model, rootView);
    }

    public void login(String name, String password) {
        httpRequestDetail(true, mModel.login(name, password), new OnRequestCallback<User>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(User user, BaseResult objResult) {
                mRootView.loginSuccess(user);
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }
}
