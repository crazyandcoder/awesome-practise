package com.crazyandcoder.top.university.mvp.presenter.school;

import android.app.Application;


import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.http.OnRequestCallback;
import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.req.SchoolListReq;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolListContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class SchoolListPresenter extends CrazyBasePresenter<SchoolListContract.Model, SchoolListContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public SchoolListPresenter(SchoolListContract.Model model, SchoolListContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

    /**
     * 学校列表
     *
     * @param schoolListReq
     */
    public void getSchoolList(boolean showLoading, SchoolListReq schoolListReq) {
        httpRequestDetail(showLoading, mModel.getSchoolList(schoolListReq), new OnRequestCallback<BasePageResult<TopSchoolResp>>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(BasePageResult<TopSchoolResp> school, BaseResult objResult) {
                mRootView.schoolListSuccess(school);
            }
        });


    }
}
