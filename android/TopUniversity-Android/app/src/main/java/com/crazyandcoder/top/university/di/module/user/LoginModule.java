package com.crazyandcoder.top.university.di.module.user;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.mvp.contract.user.LoginContract;
import com.crazyandcoder.top.university.mvp.model.user.LoginModel;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class LoginModule {

    @Binds
    abstract LoginContract.Model bindLoginModel(LoginModel model);
}