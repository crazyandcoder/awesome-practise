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

import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.req.ProfessionHotListReq;
import com.crazyandcoder.top.university.bean.req.SchoolHotListReq;
import com.crazyandcoder.top.university.bean.req.SchoolListReq;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.mvp.contract.main.HomeContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@FragmentScope
public class HomePresenter extends CrazyBasePresenter<HomeContract.Model, HomeContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public HomePresenter(HomeContract.Model model, HomeContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

    /**
     * 获取school列表
     *
     * @param schoolListReq
     */
    public void getSchoolHotList(SchoolHotListReq schoolListReq) {
        httpRequestDetail(true, mModel.getSchoolHotList(schoolListReq), new OnRequestCallback<BasePageResult<TopSchoolResp>>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(BasePageResult<TopSchoolResp> school, BaseResult objResult) {
                mRootView.schoolListSuccess(school);
            }

            @Override
            public void onFailure(int status, String resultMsg) {
                super.onFailure(status, resultMsg);
                mRootView.schoolListFailure();
            }
        });

    }

    /**
     * 获取school列表
     *
     * @param schoolListReq
     */
    public void getSchoolList(SchoolListReq schoolListReq) {
        httpRequestDetail(true, mModel.getSchoolList(schoolListReq), new OnRequestCallback<BasePageResult<TopSchoolResp>>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(BasePageResult<TopSchoolResp> school, BaseResult objResult) {
                mRootView.schoolListSuccess(school);
            }

            @Override
            public void onFailure(int status, String resultMsg) {
                super.onFailure(status, resultMsg);
                mRootView.schoolListFailure();
            }
        });

    }

    /**
     * 热门专业列表
     * @param professionHotListReq
     */
    public void getProfessionHotList(ProfessionHotListReq professionHotListReq) {
        httpRequestDetail(true, mModel.getProfessionHotList(professionHotListReq), new OnRequestCallback<BasePageResult<ProfessionHotListResp>>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(BasePageResult<ProfessionHotListResp> prfession, BaseResult objResult) {
                mRootView.ProfessionHotListSuccess(prfession);
            }

            @Override
            public void onFailure(int status, String resultMsg) {
                super.onFailure(status, resultMsg);
                mRootView.ProfessionHotListFailure();
            }
        });
    }
}
