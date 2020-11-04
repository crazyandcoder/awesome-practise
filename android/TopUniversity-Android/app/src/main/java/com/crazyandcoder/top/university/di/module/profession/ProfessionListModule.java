package com.crazyandcoder.top.university.di.module.profession;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionListContract;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolListContract;
import com.crazyandcoder.top.university.mvp.model.profession.ProfessionListModel;
import com.crazyandcoder.top.university.mvp.ui.adapter.ProfessionListAdapter;
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
public abstract class ProfessionListModule {

    @Binds
    abstract ProfessionListContract.Model bindProfessionListModel(ProfessionListModel model);


    @ActivityScope
    @Provides
    static List<ProfessionHotListResp> provideList() {
        List<ProfessionHotListResp> data = new ArrayList<>();
        return data;
    }

    @ActivityScope
    @Provides
    static ProfessionListAdapter provideProfessionListAdapter(List<ProfessionHotListResp> list) {
        ProfessionListAdapter professionListAdapter = new ProfessionListAdapter(list);
        return professionListAdapter;
    }


    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(ProfessionListContract.View view) {
        return new LinearLayoutManager(view.getLayoutManagerContext());
    }
}