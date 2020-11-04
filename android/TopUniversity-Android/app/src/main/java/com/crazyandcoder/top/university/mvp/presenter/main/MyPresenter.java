package com.crazyandcoder.top.university.mvp.presenter.main;

import android.app.Application;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.di.scope.FragmentScope;

import com.crazyandcoder.android.lib.base.http.OnRequestCallback;
import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.bean.UpdateCoreSuper;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.mvp.contract.main.MyContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@FragmentScope
public class MyPresenter extends CrazyBasePresenter<MyContract.Model, MyContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public MyPresenter(MyContract.Model model, MyContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

    public void checkUpdate() {
        httpRequestDetail(true, mModel.checkUpdate(), new OnRequestCallback<UpdateCoreSuper>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(UpdateCoreSuper update, BaseResult objResult) {
                mRootView.hasUpdate(update);
            }

            @Override
            public void onFailure(int status, String resultMsg) {
                super.onFailure(status, resultMsg);
                mRootView.updateError();
            }
        });
    }
}
