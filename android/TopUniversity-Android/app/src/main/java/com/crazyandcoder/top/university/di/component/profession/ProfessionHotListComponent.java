package com.crazyandcoder.top.university.di.component.profession;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.profession.ProfessionHotListModule;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionHotListContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.profession.ProfessionHotListActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = ProfessionHotListModule.class, dependencies = AppComponent.class)
public interface ProfessionHotListComponent {

    void inject(ProfessionHotListActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ProfessionHotListComponent.Builder view(ProfessionHotListContract.View view);

        ProfessionHotListComponent.Builder appComponent(AppComponent appComponent);

        ProfessionHotListComponent build();
    }
}