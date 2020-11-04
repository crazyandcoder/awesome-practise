package com.crazyandcoder.top.university.di.component.main;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.main.MainModule;
import com.crazyandcoder.top.university.mvp.contract.main.MainContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.main.MainActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {

    void inject(MainActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MainComponent.Builder view(MainContract.View view);

        MainComponent.Builder appComponent(AppComponent appComponent);

        MainComponent build();
    }
}