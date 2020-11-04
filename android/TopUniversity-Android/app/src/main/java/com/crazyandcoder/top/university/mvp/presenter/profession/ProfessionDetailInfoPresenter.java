package com.crazyandcoder.top.university.mvp.presenter.profession;

import android.app.Application;


import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.http.OnRequestCallback;
import com.crazyandcoder.android.lib.base.mvp.CrazyBasePresenter;
import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import com.crazyandcoder.top.university.bean.req.ProfessionHotListReq;
import com.crazyandcoder.top.university.bean.resp.ProfessionDetailInfoResp;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionDetailInfoContract;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
public class ProfessionDetailInfoPresenter extends CrazyBasePresenter<ProfessionDetailInfoContract.Model, ProfessionDetailInfoContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;

    @Inject
    public ProfessionDetailInfoPresenter(ProfessionDetailInfoContract.Model model, ProfessionDetailInfoContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mApplication = null;
    }

    /**
     * 获取专业详情
     *
     * @param professionId
     */
    public void getProfessionDetailInfo(String professionId) {
        httpRequestDetail(true, mModel.getProfessionDetailInfo(professionId), new OnRequestCallback<ProfessionDetailInfoResp>(mApplication, mErrorHandler) {
            @Override
            public void onSuccess(ProfessionDetailInfoResp prfession, BaseResult objResult) {
                mRootView.ProfessionDetailInfoSuccess(prfession);
            }

            @Override
            public void onFailure(int status, String resultMsg) {
                super.onFailure(status, resultMsg);
                mRootView.ProfessionDetailInfoFailure();
            }
        });
    }

    /**
     * 热门专业列表
     *
     * @param professionHotListReq
     */
//    public void getProfessionHotList(ProfessionHotListReq professionHotListReq) {
//        httpRequestDetail(true, mModel.getProfessionHotList(professionHotListReq), new OnRequestCallback<BasePageResult<ProfessionHotListResp>>(mApplication, mErrorHandler) {
//            @Override
//            public void onSuccess(BasePageResult<ProfessionHotListResp> prfession, BaseResult objResult) {
//                mRootView.ProfessionHotListSuccess(prfession);
//            }
//
//            @Override
//            public void onFailure(int status, String resultMsg) {
//                super.onFailure(status, resultMsg);
//                mRootView.ProfessionHotListFailure();
//            }
//        });
//    }
}
