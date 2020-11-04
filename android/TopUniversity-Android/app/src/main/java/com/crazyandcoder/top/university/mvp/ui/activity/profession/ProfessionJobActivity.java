package com.crazyandcoder.top.university.mvp.ui.activity.profession;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.top.university.di.component.profession.DaggerProfessionJobComponent;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionJobContract;
import com.crazyandcoder.top.university.mvp.presenter.profession.ProfessionJobPresenter;

import com.crazyandcoder.top.university.R;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class ProfessionJobActivity extends AbsBaseUIActivity<ProfessionJobPresenter> implements ProfessionJobContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerProfessionJobComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_profession_job;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
