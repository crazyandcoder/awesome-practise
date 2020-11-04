package com.crazyandcoder.top.university.di.component.profession;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.profession.ProfessionListModule;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionListContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.profession.ProfessionListActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = ProfessionListModule.class, dependencies = AppComponent.class)
public interface ProfessionListComponent {

    void inject(ProfessionListActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ProfessionListComponent.Builder view(ProfessionListContract.View view);

        ProfessionListComponent.Builder appComponent(AppComponent appComponent);

        ProfessionListComponent build();
    }
}