package com.crazyandcoder.top.university.mvp.contract.main;

import android.content.Context;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.mvp.ICrazyModel;
import com.crazyandcoder.android.lib.base.mvp.ICrazyView;
import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.req.ProfessionHotListReq;
import com.crazyandcoder.top.university.bean.req.SchoolHotListReq;
import com.crazyandcoder.top.university.bean.req.SchoolListReq;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public interface HomeContract {

    interface View extends ICrazyView {

        Context getLayoutManagerContext();

        void schoolListSuccess(BasePageResult<TopSchoolResp> school);

        void schoolListFailure();

        void ProfessionHotListSuccess(BasePageResult<ProfessionHotListResp> prfession);

        void ProfessionHotListFailure();

    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
     */
    interface Model extends ICrazyModel {

        /**
         * 获取学校列表
         *
         * @param schoolListReq
         * @return
         */
        Observable<BasePageResult<TopSchoolResp>> getSchoolHotList(SchoolHotListReq schoolListReq);

        /**
         * 获取学校列表
         *
         * @param schoolListReq
         * @return
         */
        Observable<BasePageResult<TopSchoolResp>> getSchoolList(SchoolListReq schoolListReq);

        /**
         * 热门专业列表
         *
         * @param professionHotListReq
         * @return
         */
        Observable<BasePageResult<ProfessionHotListResp>> getProfessionHotList(ProfessionHotListReq professionHotListReq);

    }
}
