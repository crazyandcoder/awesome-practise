package com.crazyandcoder.top.university.di.component.setting;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.setting.AdviceModule;
import com.crazyandcoder.top.university.mvp.contract.setting.AdviceContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.setting.AdviceActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = AdviceModule.class, dependencies = AppComponent.class)
public interface AdviceComponent {

    void inject(AdviceActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        AdviceComponent.Builder view(AdviceContract.View view);

        AdviceComponent.Builder appComponent(AppComponent appComponent);

        AdviceComponent build();
    }
}