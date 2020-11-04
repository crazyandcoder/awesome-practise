package com.crazyandcoder.top.university.mvp.presenter.setting;

import android.app.Application;


import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.mvp.contract.setting.AdviceContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class AdvicePresenter extends CrazyBasePresenter<AdviceContract.Model, AdviceContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public AdvicePresenter(AdviceContract.Model model, AdviceContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }
}
