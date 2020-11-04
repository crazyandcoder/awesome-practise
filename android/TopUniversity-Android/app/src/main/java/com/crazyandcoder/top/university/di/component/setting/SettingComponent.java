package com.crazyandcoder.top.university.di.component.setting;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.setting.SettingModule;
import com.crazyandcoder.top.university.mvp.contract.setting.SettingContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.setting.SettingActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = SettingModule.class, dependencies = AppComponent.class)
public interface SettingComponent {

    void inject(SettingActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SettingComponent.Builder view(SettingContract.View view);

        SettingComponent.Builder appComponent(AppComponent appComponent);

        SettingComponent build();
    }
}