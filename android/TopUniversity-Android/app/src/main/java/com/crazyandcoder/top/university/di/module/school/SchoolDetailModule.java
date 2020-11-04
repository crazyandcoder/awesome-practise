package com.crazyandcoder.top.university.di.module.school;

import dagger.Binds;
import dagger.Module;

import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailContract;
import com.crazyandcoder.top.university.mvp.model.school.SchoolDetailModel;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class SchoolDetailModule {

    @Binds
    abstract SchoolDetailContract.Model bindSchoolDetailModel(SchoolDetailModel model);
}