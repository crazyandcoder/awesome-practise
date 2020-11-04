package com.crazyandcoder.top.university.di.module.profession;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionDetailInfoContract;
import com.crazyandcoder.top.university.mvp.model.profession.ProfessionDetailInfoModel;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class ProfessionDetailInfoModule {

    @Binds
    abstract ProfessionDetailInfoContract.Model bindProfessionDetailInfoModel(ProfessionDetailInfoModel model);
}