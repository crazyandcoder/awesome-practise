package com.crazyandcoder.top.university.di.component.school;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.school.SchoolDetailModule;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.school.SchoolDetailActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = SchoolDetailModule.class, dependencies = AppComponent.class)
public interface SchoolDetailComponent {

    void inject(SchoolDetailActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SchoolDetailComponent.Builder view(SchoolDetailContract.View view);

        SchoolDetailComponent.Builder appComponent(AppComponent appComponent);

        SchoolDetailComponent build();
    }
}