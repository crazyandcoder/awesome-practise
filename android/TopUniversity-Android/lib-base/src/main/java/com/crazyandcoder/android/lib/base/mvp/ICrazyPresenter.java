package com.crazyandcoder.android.lib.base.mvp;

import android.app.Activity;

/**
 * @ClassName: IPresenter
 * @Description: The base interface for each mvp presenter.
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 6:35 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 6:35 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ICrazyPresenter {

    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     * 在框架中 {@link Activity#onDestroy()} 时会默认调用 {@link IPresenter#onDestroy()}
     */
    void onDestroy();

}
