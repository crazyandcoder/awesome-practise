package com.crazyandcoder.top.university.di.module.school;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfo;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailMoreInfoContract;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolListContract;
import com.crazyandcoder.top.university.mvp.model.school.SchoolListModel;
import com.crazyandcoder.top.university.mvp.ui.adapter.SchoolListAdapter;
import com.crazyandcoder.top.university.mvp.ui.adapter.SchoolMoreInfoAdapter;

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
public abstract class SchoolListModule {

    @Binds
    abstract SchoolListContract.Model bindSchoolListModel(SchoolListModel model);


    @ActivityScope
    @Provides
    static List<TopSchoolResp> provideList() {
        List<TopSchoolResp> data = new ArrayList<>();
        return data;
    }

    @ActivityScope
    @Provides
    static SchoolListAdapter provideSchoolListAdapter(List<TopSchoolResp> list) {
        SchoolListAdapter schoolListAdapter = new SchoolListAdapter(list);
        return schoolListAdapter;
    }


    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(SchoolListContract.View view) {
        return new LinearLayoutManager(view.getLayoutManagerContext());
    }
}