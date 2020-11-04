package com.crazyandcoder.top.university.di.component.main;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.main.HomeModule;
import com.crazyandcoder.top.university.mvp.contract.main.HomeContract;

import com.crazyandcoder.android.lib.base.di.scope.FragmentScope;
import com.crazyandcoder.top.university.mvp.ui.fragment.main.HomeFragment;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@FragmentScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {

    void inject(HomeFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeComponent.Builder view(HomeContract.View view);

        HomeComponent.Builder appComponent(AppComponent appComponent);

        HomeComponent build();
    }
}