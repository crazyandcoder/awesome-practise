package com.crazyandcoder.android.lib.base.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.crazyandcoder.android.lib.base.base.BaseApplication;
import com.crazyandcoder.android.lib.base.delegate.AppLifecycles;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.widget.status.core.LoadSir;
import com.crazyandcoder.android.lib.base.widget.status.impl.CrazyConstant;
import com.crazyandcoder.android.lib.base.widget.status.impl.CrazyEmptyCallback;
import com.crazyandcoder.android.lib.base.widget.status.impl.CrazyLoadingCallback;
import com.crazyandcoder.android.lib.base.widget.status.impl.CrazyNoNetwordCallback;

/**
 * @ClassName: AppLifecycleImpl
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:27 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:27 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppLifecycleImpl implements AppLifecycles {
    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        //全局配置默认状态页
        AppComponent appComponent = ((BaseApplication) application).getAppComponent();
        appComponent.extras().put(CrazyConstant.LoadSir.NO_NETWORK, new CrazyNoNetwordCallback());
        appComponent.extras().put(CrazyConstant.LoadSir.LOADING, new CrazyLoadingCallback());
        LoadSir.beginBuilder().addCallback(new CrazyNoNetwordCallback()).addCallback(new CrazyLoadingCallback()).commit();
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
