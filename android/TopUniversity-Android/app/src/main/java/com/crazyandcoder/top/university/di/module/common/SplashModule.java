package com.crazyandcoder.top.university.di.module.common;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.mvp.contract.common.SplashContract;
import com.crazyandcoder.top.university.mvp.model.common.SplashModel;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class SplashModule {

    @Binds
    abstract SplashContract.Model bindSplashModel(SplashModel model);
}