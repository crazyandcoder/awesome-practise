package com.crazyandcoder.android.lib.base.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.crazyandcoder.android.lib.base.delegate.AppLifecycles;
import com.crazyandcoder.android.lib.base.di.module.GlobalConfigModule;
import com.crazyandcoder.android.lib.base.integration.ConfigModule;

import java.util.List;

/**
 * @ClassName: AppConfig
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:26 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:26 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppConfiguration implements ConfigModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlobalConfigModule.Builder builder) {

    }

    @Override
    public void injectAppLifecycle(@NonNull Context context, @NonNull List<AppLifecycles> lifecycles) {
        lifecycles.add(new AppLifecycleImpl());
    }

    @Override
    public void injectActivityLifecycle(@NonNull Context context, @NonNull List<Application.ActivityLifecycleCallbacks> lifecycles) {

    }

    @Override
    public void injectFragmentLifecycle(@NonNull Context context, @NonNull List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }
}
