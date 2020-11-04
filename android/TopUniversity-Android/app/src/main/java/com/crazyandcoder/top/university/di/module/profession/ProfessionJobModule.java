package com.crazyandcoder.top.university.di.module.profession;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionJobContract;
import com.crazyandcoder.top.university.mvp.model.profession.ProfessionJobModel;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class ProfessionJobModule {

    @Binds
    abstract ProfessionJobContract.Model bindProfessionJobModel(ProfessionJobModel model);
}