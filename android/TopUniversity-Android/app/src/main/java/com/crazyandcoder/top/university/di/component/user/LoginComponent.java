package com.crazyandcoder.top.university.di.component.user;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.user.LoginModule;
import com.crazyandcoder.top.university.mvp.contract.user.LoginContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.user.LoginActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        LoginComponent.Builder view(LoginContract.View view);

        LoginComponent.Builder appComponent(AppComponent appComponent);

        LoginComponent build();
    }
}