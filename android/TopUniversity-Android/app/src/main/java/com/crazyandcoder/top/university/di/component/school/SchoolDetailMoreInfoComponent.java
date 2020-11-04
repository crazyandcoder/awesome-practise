package com.crazyandcoder.top.university.di.component.school;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.school.SchoolDetailMoreInfoModule;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailMoreInfoContract;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import com.crazyandcoder.top.university.mvp.ui.activity.school.SchoolDetailMoreInfoActivity;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@ActivityScope
@Component(modules = SchoolDetailMoreInfoModule.class, dependencies = AppComponent.class)
public interface SchoolDetailMoreInfoComponent {

    void inject(SchoolDetailMoreInfoActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SchoolDetailMoreInfoComponent.Builder view(SchoolDetailMoreInfoContract.View view);

        SchoolDetailMoreInfoComponent.Builder appComponent(AppComponent appComponent);

        SchoolDetailMoreInfoComponent build();
    }
}