package com.crazyandcoder.top.university.di.component.profession;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.profession.ProfessionJobModule;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionJobContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.profession.ProfessionJobActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = ProfessionJobModule.class, dependencies = AppComponent.class)
public interface ProfessionJobComponent {

    void inject(ProfessionJobActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ProfessionJobComponent.Builder view(ProfessionJobContract.View view);

        ProfessionJobComponent.Builder appComponent(AppComponent appComponent);

        ProfessionJobComponent build();
    }
}