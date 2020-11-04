/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.crazyandcoder.android.lib.base.delegate;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.ComponentCallbacks2;
import android.content.ContentProvider;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.crazyandcoder.android.lib.base.base.IApplication;
import com.crazyandcoder.android.lib.base.callback.AppComponentCallbacks;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.di.component.DaggerAppComponent;
import com.crazyandcoder.android.lib.base.di.module.GlobalConfigModule;
import com.crazyandcoder.android.lib.base.integration.ConfigModule;
import com.crazyandcoder.android.lib.base.integration.ManifestParser;
import com.crazyandcoder.android.lib.base.integration.cache.IntelligentCache;
import com.crazyandcoder.android.lib.base.utils.Preconditions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * ================================================
 * ApplicationDelegateImpl 可以代理 Application 的生命周期,在对应的生命周期,执行对应的逻辑,因为 Java 只能单继承
 * 所以当遇到某些三方库需要继承于它的 Application 的时候,就只有自定义 Application 并继承于三方库的 Application
 * 这时就不用再继承 BaseApplication,只用在自定义Application中对应的生命周期调用ApplicationDelegateImpl对应的方法
 * (Application一定要实现APP接口),框架就能照常运行
 * ================================================
 *
 * @author admin
 */
public class ApplicationDelegateImpl implements IApplication, AppLifecycles {

    @Inject
    @Named("ActivityLifecycle")
    protected Application.ActivityLifecycleCallbacks mActivityLifecycle;

    @Inject
    @Named("ActivityLifecycleForRxLifecycle")
    protected Application.ActivityLifecycleCallbacks mActivityLifecycleForRxLifecycle;

    private Application mApplication;
    private AppComponent mAppComponent;
    private List<ConfigModule> mModules;
    private List<AppLifecycles> mAppLifecycles = new ArrayList<>();
    private List<Application.ActivityLifecycleCallbacks> mActivityLifecycles = new ArrayList<>();
    private ComponentCallbacks2 mComponentCallback;

    public ApplicationDelegateImpl(@NonNull Context context) {

        //用反射, 将 AndroidManifest.xml 中带有 ConfigModule 标签的 class 转成对象集合（List<ConfigModule>）
        this.mModules = new ManifestParser(context).parse();

        //遍历之前获得的集合, 执行每一个 ConfigModule 实现类的某些方法
        for (ConfigModule module : mModules) {

            //将框架外部, 开发者实现的 Application 的生命周期回调 (AppLifecycles) 存入 mAppLifecycles 集合 (此时还未注册回调)
            module.injectAppLifecycle(context, mAppLifecycles);

            //将框架外部, 开发者实现的 Activity 的生命周期回调 (ActivityLifecycleCallbacks) 存入 mActivityLifecycles 集合 (此时还未注册回调)
            module.injectActivityLifecycle(context, mActivityLifecycles);
        }
    }

    @Override
    public void attachBaseContext(@NonNull Context base) {
        //遍历 mAppLifecycles, 执行所有已注册的 AppLifecycles 的 attachBaseContext() 方法 (框架外部, 开发者扩展的逻辑)
        for (AppLifecycles lifecycle : mAppLifecycles) {
            lifecycle.attachBaseContext(base);
        }
    }

    @Override
    public void onCreate(@NonNull Application application) {
        this.mApplication = application;
        mAppComponent = DaggerAppComponent
                .builder()
                .application(mApplication)
                .globalConfigModule(getGlobalConfigModule(mApplication, mModules))
                .build();
        mAppComponent.inject(this);

        //将 ConfigModule 的实现类的集合存放到缓存 Cache, 可以随时获取
        //使用 IntelligentCache.KEY_KEEP 作为 key 的前缀, 可以使储存的数据永久存储在内存中
        //否则存储在 LRU 算法的存储空间中 (大于或等于缓存所能允许的最大 size, 则会根据 LRU 算法清除之前的条目)
        //前提是 extras 使用的是 IntelligentCache (框架默认使用)
        mAppComponent.extras().put(IntelligentCache.getKeyOfKeep(ConfigModule.class.getName()), mModules);

        this.mModules = null;

        //注册框架内部已实现的 Activity 生命周期逻辑
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycle);

        //注册框架内部已实现的 RxLifecycle 逻辑
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleForRxLifecycle);

        //注册框架外部, 开发者扩展的 Activity 生命周期逻辑
        //每个 ConfigModule 的实现类可以声明多个 Activity 的生命周期回调
        //也可以有 N 个 ConfigModule 的实现类 (完美支持组件化项目 各个 Module 的各种独特需求)
        for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
            mApplication.registerActivityLifecycleCallbacks(lifecycle);
        }

        mComponentCallback = new AppComponentCallbacks(mApplication, mAppComponent);

        //注册回掉: 内存紧张时释放部分内存
        mApplication.registerComponentCallbacks(mComponentCallback);

        //执行框架外部, 开发者扩展的 App onCreate 逻辑
        for (AppLifecycles lifecycle : mAppLifecycles) {
            lifecycle.onCreate(mApplication);
        }
    }

    @Override
    public void onTerminate(@NonNull Application application) {
        if (mActivityLifecycle != null) {
            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycle);
        }
        if (mActivityLifecycleForRxLifecycle != null) {
            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycleForRxLifecycle);
        }
        if (mComponentCallback != null) {
            mApplication.unregisterComponentCallbacks(mComponentCallback);
        }
        if (mActivityLifecycles != null && mActivityLifecycles.size() > 0) {
            for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
                mApplication.unregisterActivityLifecycleCallbacks(lifecycle);
            }
        }
        if (mAppLifecycles != null && mAppLifecycles.size() > 0) {
            for (AppLifecycles lifecycle : mAppLifecycles) {
                lifecycle.onTerminate(mApplication);
            }
        }
        this.mAppComponent = null;
        this.mActivityLifecycle = null;
        this.mActivityLifecycleForRxLifecycle = null;
        this.mActivityLifecycles = null;
        this.mComponentCallback = null;
        this.mAppLifecycles = null;
        this.mApplication = null;
    }

    /**
     * 将app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     * 需要在AndroidManifest中声明{@link ConfigModule}的实现类,和Glide的配置方式相似
     *
     * @return GlobalConfigModule
     */
    private GlobalConfigModule getGlobalConfigModule(Context context, List<ConfigModule> modules) {
        GlobalConfigModule.Builder builder = GlobalConfigModule
                .builder();

        //遍历 ConfigModule 集合, 给全局配置 GlobalConfigModule 添加参数
        for (ConfigModule module : modules) {
            module.applyOptions(context, builder);
        }

        return builder.build();
    }

    /**
     * 将 {@link AppComponent} 返回出去, 供其它地方使用, {@link AppComponent} 接口中声明的方法返回的实例, 在 {@link #getAppComponent()} 拿到对象后都可以直接使用
     *
     * @return AppComponent
     * @see com.crazyandcoder.android.lib.base.utils.CrazyUtils#obtainAppComponentFromContext(Context) 可直接获取 {@link AppComponent}
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(mAppComponent,
                "%s == null, first call %s#onCreate(Application) in %s#onCreate()",
                AppComponent.class.getName(), getClass().getName(), mApplication == null
                        ? Application.class.getName() : mApplication.getClass().getName());
        return mAppComponent;
    }


}

