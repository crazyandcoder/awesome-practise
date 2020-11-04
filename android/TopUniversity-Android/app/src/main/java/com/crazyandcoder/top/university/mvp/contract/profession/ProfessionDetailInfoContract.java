package com.crazyandcoder.top.university.mvp.contract.profession;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.mvp.ICrazyModel;
import com.crazyandcoder.android.lib.base.mvp.ICrazyView;
import com.crazyandcoder.top.university.bean.req.ProfessionHotListReq;
import com.crazyandcoder.top.university.bean.resp.ProfessionDetailInfoResp;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public interface ProfessionDetailInfoContract {

    interface View extends ICrazyView {
//        void ProfessionHotListSuccess(BasePageResult<ProfessionHotListResp> prfession);
//
//        void ProfessionHotListFailure();

        void ProfessionDetailInfoSuccess(ProfessionDetailInfoResp prfession);

        void ProfessionDetailInfoFailure();

    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
     */
    interface Model extends ICrazyModel {

        /**
         * 热门专业列表
         *
         * @param professionHotListReq
         * @return
         */
        Observable<BasePageResult<ProfessionHotListResp>> getProfessionHotList(ProfessionHotListReq professionHotListReq);

        Observable<BaseResult<ProfessionDetailInfoResp>> getProfessionDetailInfo(String professionId);

    }
}
