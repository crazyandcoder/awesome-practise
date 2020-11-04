package com.crazyandcoder.top.university.di.component.main;

import dagger.BindsInstance;
import dagger.Component;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.module.main.MyModule;
import com.crazyandcoder.top.university.mvp.contract.main.MyContract;

import com.crazyandcoder.android.lib.base.di.scope.FragmentScope;
import com.crazyandcoder.top.university.mvp.ui.fragment.main.MyFragment;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@FragmentScope
@Component(modules = MyModule.class, dependencies = AppComponent.class)
public interface MyComponent {

    void inject(MyFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyComponent.Builder view(MyContract.View view);

        MyComponent.Builder appComponent(AppComponent appComponent);

        MyComponent build();
    }
}