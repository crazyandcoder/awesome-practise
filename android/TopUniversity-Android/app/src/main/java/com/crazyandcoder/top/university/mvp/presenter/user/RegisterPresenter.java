package com.crazyandcoder.top.university.mvp.presenter.user;

import android.app.Application;


import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.http.OnRequestCallback;
import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.bean.User;
import com.crazyandcoder.top.university.mvp.contract.user.RegisterContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class RegisterPresenter extends CrazyBasePresenter<RegisterContract.Model, RegisterContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public RegisterPresenter(RegisterContract.Model model, RegisterContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

    public void register(String name, String pwd, String cpwd) {
        httpRequestDetail(true, mModel.register(name, pwd,cpwd), new OnRequestCallback<User>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(User user, BaseResult objResult) {
                mRootView.registerSuccess(name);
            }
        });
    }
}
