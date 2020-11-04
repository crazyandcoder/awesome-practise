package com.crazyandcoder.top.university.mvp.presenter.school;

import android.app.Application;


import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.http.OnRequestCallback;
import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfoResp;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailMoreInfoContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class SchoolDetailMoreInfoPresenter extends CrazyBasePresenter<SchoolDetailMoreInfoContract.Model, SchoolDetailMoreInfoContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public SchoolDetailMoreInfoPresenter(SchoolDetailMoreInfoContract.Model model, SchoolDetailMoreInfoContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

    public void getSchoolMoreInfo(String schoolId) {
        httpRequestDetail(true, mModel.getSchoolDetailMoreInfo(schoolId), new OnRequestCallback<SchoolMoreInfoResp>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(SchoolMoreInfoResp school, BaseResult objResult) {
                mRootView.schoolDetailMoreInfoSuccess(school);
            }
        });

    }
}
