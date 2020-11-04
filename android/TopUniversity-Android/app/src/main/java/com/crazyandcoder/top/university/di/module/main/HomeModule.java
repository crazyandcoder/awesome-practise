package com.crazyandcoder.top.university.di.module.main;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.base.di.scope.FragmentScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.mvp.contract.main.HomeContract;
import com.crazyandcoder.top.university.mvp.model.main.HomeModel;
import com.crazyandcoder.top.university.mvp.ui.adapter.RecommendSchoolListAdapter;
import com.crazyandcoder.top.university.mvp.ui.adapter.SchoolListAdapter;

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
public abstract class HomeModule {

    @Binds
    abstract HomeContract.Model bindHomeModel(HomeModel model);

    @FragmentScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(HomeContract.View view) {
        return new LinearLayoutManager(view.getLayoutManagerContext());
    }

    @FragmentScope
    @Provides
    static List<TopSchoolResp> provideList() {
        return new ArrayList<>();
    }


    @FragmentScope
    @Provides
    static RecyclerView.Adapter provideRecommendSchoolListAdapter(List<TopSchoolResp> list) {
        return new RecommendSchoolListAdapter(list);
    }



}