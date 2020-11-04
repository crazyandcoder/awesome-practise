package com.crazyandcoder.android.lib.base.base;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.crazyandcoder.android.lib.base.delegate.ApplicationDelegateImpl;
import com.crazyandcoder.android.lib.base.delegate.AppLifecycles;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.utils.Preconditions;

/**
 * @ClassName: BaseApplication
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/19 3:37 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/19 3:37 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseApplication extends Application implements IApplication {
    private AppLifecycles mAppDelegate;
    private static Context appContext;

    /**
     * 这里会在 {@link BaseApplication#onCreate} 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null) {
            this.mAppDelegate = new ApplicationDelegateImpl(base);
        }
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        if (mAppDelegate != null) {
            this.mAppDelegate.onCreate(this);
        }
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null) {
            this.mAppDelegate.onTerminate(this);
        }
    }

    /**
     * 将 {@link AppComponent} 返回出去, 供其它地方使用, {@link AppComponent} 接口中声明的方法所返回的实例, 在 {@link #getAppComponent()} 拿到对象后都可以直接使用
     *
     * @return AppComponent
     * @see CrazyUtils#obtainAppComponentFromContext(Context) 可直接获取 {@link AppComponent}
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(mAppDelegate, "%s cannot be null", ApplicationDelegateImpl.class.getName());
        Preconditions.checkState(mAppDelegate instanceof IApplication, "%s must be implements %s", mAppDelegate.getClass().getName(), IApplication.class.getName());
        return ((IApplication) mAppDelegate).getAppComponent();
    }

    public static Context getAppContext() {
        return appContext;
    }

}
