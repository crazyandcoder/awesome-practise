package com.crazyandcoder.top.university.di.module.main;

import com.crazyandcoder.android.lib.base.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.mvp.contract.main.MyContract;
import com.crazyandcoder.top.university.mvp.model.main.MyModel;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class MyModule {

    @Binds
    abstract MyContract.Model bindMyModel(MyModel model);
}