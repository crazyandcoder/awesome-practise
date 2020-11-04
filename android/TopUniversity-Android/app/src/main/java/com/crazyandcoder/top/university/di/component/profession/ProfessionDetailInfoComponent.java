package com.crazyandcoder.top.university.di.component.profession;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.profession.ProfessionDetailInfoModule;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionDetailInfoContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.profession.ProfessionDetailInfoActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = ProfessionDetailInfoModule.class, dependencies = AppComponent.class)
public interface ProfessionDetailInfoComponent {

    void inject(ProfessionDetailInfoActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ProfessionDetailInfoComponent.Builder view(ProfessionDetailInfoContract.View view);

        ProfessionDetailInfoComponent.Builder appComponent(AppComponent appComponent);

        ProfessionDetailInfoComponent build();
    }
}