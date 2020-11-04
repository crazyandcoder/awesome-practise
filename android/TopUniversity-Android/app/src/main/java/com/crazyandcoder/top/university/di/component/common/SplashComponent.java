package com.crazyandcoder.top.university.di.component.common;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.common.SplashModule;
import com.crazyandcoder.top.university.mvp.contract.common.SplashContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.common.SplashActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = SplashModule.class, dependencies = AppComponent.class)
public interface SplashComponent {

    void inject(SplashActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SplashComponent.Builder view(SplashContract.View view);

        SplashComponent.Builder appComponent(AppComponent appComponent);

        SplashComponent build();
    }
}