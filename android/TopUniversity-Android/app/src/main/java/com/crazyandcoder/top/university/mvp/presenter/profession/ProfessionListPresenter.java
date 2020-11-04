package com.crazyandcoder.top.university.mvp.presenter.profession;

import android.app.Application;


import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.http.OnRequestCallback;
import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.bean.req.ProfessionListReq;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionListContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class ProfessionListPresenter extends CrazyBasePresenter<ProfessionListContract.Model, ProfessionListContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public ProfessionListPresenter(ProfessionListContract.Model model, ProfessionListContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

    public void getProfessionList(ProfessionListReq professionListReq) {

        httpRequestDetail(true, mModel.getProfessionList(professionListReq), new OnRequestCallback<BasePageResult<ProfessionHotListResp>>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(BasePageResult<ProfessionHotListResp> school, BaseResult objResult) {
                mRootView.professionListSuccess(school);
            }
        });
    }
}
