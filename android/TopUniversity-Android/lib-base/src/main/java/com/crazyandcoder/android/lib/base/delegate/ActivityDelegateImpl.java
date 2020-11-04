package com.crazyandcoder.android.lib.base.delegate;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.integration.EventBusManager;
import com.crazyandcoder.android.lib.base.utils.CrazyUtils;

/**
 * @ClassName: ActivityDelegateImpl
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/11 6:21 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/11 6:21 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ActivityDelegateImpl implements ActivityDelegate {
    private Activity mActivity;
    private IActivity iActivity;

    public ActivityDelegateImpl(@NonNull Activity activity) {
        this.mActivity = activity;
        this.iActivity = (IActivity) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //如果要使用 EventBus 请将此方法返回 true
        if (iActivity.useEventBus()) {
            //注册到事件主线
            EventBusManager.getInstance().register(mActivity);
        }

        //这里提供 AppComponent 对象给 BaseActivity 的子类, 用于 Dagger2 的依赖注入
        iActivity.setupActivityComponent(CrazyUtils.obtainAppComponentFromContext(mActivity));
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onDestroy() {
        //如果要使用 EventBus 请将此方法返回 true
        if (iActivity != null && iActivity.useEventBus()) {
            EventBusManager.getInstance().unregister(mActivity);
        }
        this.iActivity = null;
        this.mActivity = null;
    }
}
