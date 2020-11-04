package com.crazyandcoder.top.university.di.component.user;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.user.RegisterModule;
import com.crazyandcoder.top.university.mvp.contract.user.RegisterContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.user.RegisterActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = RegisterModule.class, dependencies = AppComponent.class)
public interface RegisterComponent {

    void inject(RegisterActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        RegisterComponent.Builder view(RegisterContract.View view);

        RegisterComponent.Builder appComponent(AppComponent appComponent);

        RegisterComponent build();
    }
}