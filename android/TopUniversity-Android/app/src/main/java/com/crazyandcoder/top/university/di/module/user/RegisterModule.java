package com.crazyandcoder.top.university.di.module.user;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.mvp.contract.user.RegisterContract;
import com.crazyandcoder.top.university.mvp.model.user.RegisterModel;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class RegisterModule {

    @Binds
    abstract RegisterContract.Model bindRegisterModel(RegisterModel model);
}