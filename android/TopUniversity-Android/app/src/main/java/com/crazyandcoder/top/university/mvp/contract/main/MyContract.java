package com.crazyandcoder.top.university.mvp.contract.main;

import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.mvp.ICrazyModel;
import com.crazyandcoder.android.lib.base.mvp.ICrazyView;
import com.crazyandcoder.top.university.bean.UpdateCoreSuper;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public interface MyContract {

    interface View extends ICrazyView {

        void hasUpdate(UpdateCoreSuper update);

        void updateError();

    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
     */
    interface Model extends ICrazyModel {

        Observable<BaseResult<UpdateCoreSuper>> checkUpdate();
    }
}
