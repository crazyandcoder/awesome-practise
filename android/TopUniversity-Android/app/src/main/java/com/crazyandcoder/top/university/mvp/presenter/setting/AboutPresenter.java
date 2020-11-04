package com.crazyandcoder.top.university.mvp.presenter.setting;

import android.app.Application;


import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.mvp.contract.setting.AboutContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class AboutPresenter extends CrazyBasePresenter<AboutContract.Model, AboutContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public AboutPresenter(AboutContract.Model model, AboutContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }
}
