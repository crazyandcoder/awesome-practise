package com.crazyandcoder.top.university.mvp.contract.school;

import android.content.Context;

import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.mvp.ICrazyModel;
import com.crazyandcoder.android.lib.base.mvp.ICrazyView;
import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfoResp;

import io.reactivex.Observable;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public interface SchoolDetailMoreInfoContract {

    interface View extends ICrazyView {

        Context getLayoutManagerContext();

        void schoolDetailMoreInfoSuccess(SchoolMoreInfoResp school);
    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
     */
    interface Model extends ICrazyModel {

        Observable<BaseResult<SchoolMoreInfoResp>> getSchoolDetailMoreInfo(String schoolId);
    }
}
