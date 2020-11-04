package com.crazyandcoder.top.university.di.module.setting;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.mvp.contract.setting.AdviceContract;
import com.crazyandcoder.top.university.mvp.model.setting.AdviceModel;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class AdviceModule {

    @Binds
    abstract AdviceContract.Model bindAdviceModel(AdviceModel model);
}