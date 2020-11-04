package com.crazyandcoder.top.university.di.component.school;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.school.SchoolListModule;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolListContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.school.SchoolListActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = SchoolListModule.class, dependencies = AppComponent.class)
public interface SchoolListComponent {

    void inject(SchoolListActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SchoolListComponent.Builder view(SchoolListContract.View view);

        SchoolListComponent.Builder appComponent(AppComponent appComponent);

        SchoolListComponent build();
    }
}