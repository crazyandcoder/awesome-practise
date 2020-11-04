package com.crazyandcoder.top.university.di.module.school;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.base.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.crazyandcoder.android.lib.base.di.scope.FragmentScope;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfo;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfoResp;
import com.crazyandcoder.top.university.mvp.contract.main.HomeContract;
import com.crazyandcoder.top.university.mvp.contract.main.MainContract;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailMoreInfoContract;
import com.crazyandcoder.top.university.mvp.model.school.SchoolDetailMoreInfoModel;
import com.crazyandcoder.top.university.mvp.ui.adapter.MainTabViewPagerAdapter;
import com.crazyandcoder.top.university.mvp.ui.adapter.SchoolMoreInfoAdapter;
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
public abstract class SchoolDetailMoreInfoModule {

    @Binds
    abstract SchoolDetailMoreInfoContract.Model bindSchoolDetailMoreInfoModel(SchoolDetailMoreInfoModel model);

    @ActivityScope
    @Provides
    static List<SchoolMoreInfo> provideList() {
        List<SchoolMoreInfo> data = new ArrayList<>();
        return data;
    }

    @ActivityScope
    @Provides
    static SchoolMoreInfoAdapter provideSchoolMoreInfoAdapter(List<SchoolMoreInfo> list) {
        SchoolMoreInfoAdapter schoolMoreInfoAdapter = new SchoolMoreInfoAdapter(list);
        return schoolMoreInfoAdapter;
    }


    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(SchoolDetailMoreInfoContract.View view) {
        return new LinearLayoutManager(view.getLayoutManagerContext());
    }
}