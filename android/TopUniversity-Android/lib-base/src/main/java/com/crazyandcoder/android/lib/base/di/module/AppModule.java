package com.crazyandcoder.android.lib.base.di.module;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.crazyandcoder.android.lib.base.integration.ActivityLifecycle;
import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.android.lib.base.integration.FragmentLifecycle;
import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.integration.RepositoryManager;
import com.crazyandcoder.android.lib.base.integration.cache.Cache;
import com.crazyandcoder.android.lib.base.integration.cache.CacheType;
import com.crazyandcoder.android.lib.base.integration.lifecycle.ActivityLifecycleForRxLifecycle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @ClassName: AppModule
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/11 6:27 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/11 6:27 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static Gson provideGson(Application application, @Nullable GsonConfiguration configuration) {
        GsonBuilder builder = new GsonBuilder();
        if (configuration != null) {
            configuration.configGson(application, builder);
        }
        return builder.create();
    }

    /**
     * 之前 {@link AppManager} 使用 Dagger 保证单例, 只能使用 {@link AppComponent#appManager()} 访问
     * 现在直接将 AppManager 独立为单例类, 可以直接通过静态方法 {@link AppManager#getAppManager()} 访问, 更加方便
     * 但为了不影响之前使用 {@link AppComponent#appManager()} 获取 {@link AppManager} 的项目, 所以暂时保留这种访问方式
     *
     * @param application
     * @return
     */
    @Singleton
    @Provides
    static AppManager provideAppManager(Application application) {
        return AppManager.getAppManager().init(application);
    }

    @Singleton
    @Provides
    static Cache<String, Object> provideExtras(Cache.Factory cacheFactory) {
        //noinspection unchecked
        return cacheFactory.build(CacheType.EXTRAS);
    }

    @Singleton
    @Provides
    static List<FragmentManager.FragmentLifecycleCallbacks> provideFragmentLifecycles() {
        return new ArrayList<>();
    }

    @Binds
    abstract IRepositoryManager bindRepositoryManager(RepositoryManager repositoryManager);

    @Binds
    @Named("ActivityLifecycle")
    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycle(ActivityLifecycle activityLifecycle);

    @Binds
    @Named("ActivityLifecycleForRxLifecycle")
    abstract Application.ActivityLifecycleCallbacks bindActivityLifecycleForRxLifecycle(ActivityLifecycleForRxLifecycle activityLifecycleForRxLifecycle);

    @Binds
    abstract FragmentManager.FragmentLifecycleCallbacks bindFragmentLifecycle(FragmentLifecycle fragmentLifecycle);

    public interface GsonConfiguration {
        void configGson(@NonNull Context context, @NonNull GsonBuilder builder);
    }
}

