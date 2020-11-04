package com.crazyandcoder.top.university.mvp.presenter.profession;

import android.app.Application;


import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionHotListContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class ProfessionHotListPresenter extends CrazyBasePresenter<ProfessionHotListContract.Model, ProfessionHotListContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public ProfessionHotListPresenter(ProfessionHotListContract.Model model, ProfessionHotListContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }
}
