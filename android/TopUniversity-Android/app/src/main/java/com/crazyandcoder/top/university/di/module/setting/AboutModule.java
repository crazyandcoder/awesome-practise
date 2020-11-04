package com.crazyandcoder.top.university.di.module.setting;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.mvp.contract.setting.AboutContract;
import com.crazyandcoder.top.university.mvp.model.setting.AboutModel;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class AboutModule {

    @Binds
    abstract AboutContract.Model bindAboutModel(AboutModel model);
}