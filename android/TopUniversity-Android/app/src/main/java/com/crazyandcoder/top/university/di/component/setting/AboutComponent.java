package com.crazyandcoder.top.university.di.component.setting;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.setting.AboutModule;
import com.crazyandcoder.top.university.mvp.contract.setting.AboutContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.setting.AboutActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = AboutModule.class, dependencies = AppComponent.class)
public interface AboutComponent {

    void inject(AboutActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AboutComponent.Builder view(AboutContract.View view);

        AboutComponent.Builder appComponent(AppComponent appComponent);

        AboutComponent build();
    }
}