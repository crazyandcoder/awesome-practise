package com.crazyandcoder.top.university.mvp.contract.profession;

import android.content.Context;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.mvp.ICrazyModel;
import com.crazyandcoder.android.lib.base.mvp.ICrazyView;
import com.crazyandcoder.top.university.bean.req.ProfessionListReq;
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
public interface ProfessionListContract {

    interface View extends ICrazyView {

        Context getLayoutManagerContext();

        void professionListSuccess(BasePageResult<ProfessionHotListResp> school);
    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
     */
    interface Model extends ICrazyModel {

        Observable<BasePageResult<ProfessionHotListResp>>  getProfessionList(ProfessionListReq professionListReq);

    }
}
