package com.crazyandcoder.top.university.di.module.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.mvp.contract.main.MainContract;
import com.crazyandcoder.top.university.mvp.model.main.MainModel;
import com.crazyandcoder.top.university.mvp.ui.adapter.MainTabViewPagerAdapter;
import com.crazyandcoder.top.university.mvp.ui.fragment.main.HomeFragment;
import com.crazyandcoder.top.university.mvp.ui.fragment.main.MyFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
@Module
public abstract class MainModule {

    @Binds
    abstract MainContract.Model bindMainModel(MainModel model);

    @ActivityScope
    @Provides
    static List<Fragment> provideList() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(MyFragment.newInstance());
        return fragmentList;
    }

    @ActivityScope
    @Provides
    static FragmentManager provideFragmentManager(MainContract.View view) {
        return view.getMainTabViewPagerSupportFragmentManager();
    }

    @ActivityScope
    @Provides
    static FragmentStatePagerAdapter provideFragmentStatePagerAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        MainTabViewPagerAdapter bossMainTabViewPagerAdapter = new MainTabViewPagerAdapter(fragmentManager, list);
        return bossMainTabViewPagerAdapter;
    }
}