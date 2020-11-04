package com.crazyandcoder.top.university.mvp.presenter.school;

import android.app.Application;


import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.http.OnRequestCallback;
import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.req.SchoolDetailInfoReq;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class SchoolDetailPresenter extends CrazyBasePresenter<SchoolDetailContract.Model, SchoolDetailContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public SchoolDetailPresenter(SchoolDetailContract.Model model, SchoolDetailContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

    public void getSchoolDetailInfo(SchoolDetailInfoReq schoolDetailInfoReq) {
        httpRequestDetail(true, mModel.getSchoolDetail(schoolDetailInfoReq), new OnRequestCallback<School>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(School school, BaseResult objResult) {
                mRootView.schoolDetailSuccess(school);
            }
        });


    }
}
