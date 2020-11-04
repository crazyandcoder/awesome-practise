package com.crazyandcoder.top.university.mvp.ui.activity.common;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.android.lib.common.utils.rxjava.RxJavaUtils;
import com.crazyandcoder.top.university.di.component.common.DaggerSplashComponent;
import com.crazyandcoder.top.university.mvp.contract.common.SplashContract;
import com.crazyandcoder.top.university.mvp.contract.main.MainContract;
import com.crazyandcoder.top.university.mvp.presenter.common.SplashPresenter;

import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.mvp.ui.activity.main.MainActivity;

import io.reactivex.functions.Consumer;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class SplashActivity extends AbsBaseUIActivity<SplashPresenter> implements SplashContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerSplashComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        RxJavaUtils.delay(1, new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                gotoNext();

            }
        });
    }

    private void gotoNext() {
        AppManager.getAppManager().startActivity(MainActivity.class);
        AppManager.getAppManager().stopActivity(SplashActivity.this);
    }
}
