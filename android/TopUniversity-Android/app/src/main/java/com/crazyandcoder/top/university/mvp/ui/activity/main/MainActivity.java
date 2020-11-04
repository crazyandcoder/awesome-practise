package com.crazyandcoder.top.university.mvp.ui.activity.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.android.lib.common.widget.bottomtab.CrazyPageBottomTabView;
import com.crazyandcoder.android.lib.common.widget.bottomtab.controller.NavigationController;
import com.crazyandcoder.android.lib.common.widget.bottomtab.item.AbsBaseTabItem;
import com.crazyandcoder.android.lib.common.widget.bottomtab.item.NormalItemView;
import com.crazyandcoder.android.lib.common.widget.bottomtab.listener.SimpleTabItemSelectedListener;
import com.crazyandcoder.android.lib.common.widget.viewpager.CrazyCustomNoTouchViewPager;
import com.crazyandcoder.top.university.di.component.main.DaggerMainComponent;
import com.crazyandcoder.top.university.mvp.contract.main.MainContract;
import com.crazyandcoder.top.university.mvp.presenter.main.MainPresenter;

import com.crazyandcoder.top.university.R;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class MainActivity extends AbsBaseUIActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.boss_main_tab_viewpager)
    CrazyCustomNoTouchViewPager bossMainTabViewpager;
    @BindView(R.id.boss_main_tab)
    CrazyPageBottomTabView bossMainTab;

    @Inject
    FragmentStatePagerAdapter mMainTabPageAdapter;
    /**
     * 首页tab
     */
    private AbsBaseTabItem mIndexTabItem = null;
    /**
     * 我的tab
     */
    private AbsBaseTabItem mMyTabItem = null;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mIndexTabItem =
                createTabItem(R.drawable.ic_round_home_normal,
                        R.drawable.ic_round_home,
                        getResources().getString(R.string.home));

        mMyTabItem =
                createTabItem(R.drawable.ic_round_my_normal,
                        R.drawable.ic_round_my,
                        getResources().getString(R.string.my));


        NavigationController navigationController = bossMainTab
                .custom()
                .addItem(mIndexTabItem)
                .addItem(mMyTabItem)
                .build();
        bossMainTabViewpager.setAdapter(mMainTabPageAdapter);
        bossMainTabViewpager.setOffscreenPageLimit(2);
        navigationController.setupWithViewPager(bossMainTabViewpager);
        navigationController.addSimpleTabItemSelectedListener(new SimpleTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
            }
        });
        setStatusBarTrans();

    }


    @Override
    public boolean showToolbar() {
        return false;
    }

    /**
     * 创建底部的tab item
     *
     * @param mipmap
     * @param checkedDrawable
     * @param text
     * @return
     */
    private AbsBaseTabItem createTabItem(int mipmap, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(mipmap, checkedDrawable, text);
        normalItemView.setTextDefaultColor(0XFFBEBEBE);
        normalItemView.setTextCheckedColor(0XFF3496FA);
        return normalItemView;
    }

    @Override
    public FragmentManager getMainTabViewPagerSupportFragmentManager() {
        return getSupportFragmentManager();
    }
}
