package com.crazyandcoder.top.university.mvp.ui.activity.school;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.Constant;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfo;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfoResp;
import com.crazyandcoder.top.university.di.component.school.DaggerSchoolDetailMoreInfoComponent;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailMoreInfoContract;
import com.crazyandcoder.top.university.mvp.presenter.school.SchoolDetailMoreInfoPresenter;
import com.crazyandcoder.top.university.mvp.ui.adapter.SchoolMoreInfoAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class SchoolDetailMoreInfoActivity extends AbsBaseUIActivity<SchoolDetailMoreInfoPresenter> implements SchoolDetailMoreInfoContract.View {

    @BindView(R.id.school_info_recycler)
    RecyclerView schoolInfoRecycler;

    @Inject
    List<SchoolMoreInfo> data;

    @Inject
    SchoolMoreInfoAdapter schoolMoreInfoAdapter;

    @Inject
    RecyclerView.LayoutManager layoutManager;

    private String schoolId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerSchoolDetailMoreInfoComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_school_detail_more_info;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        schoolId = this.getIntent().getStringExtra(Constant.Page.PAGE_KEY);
        mCommonToolbar.setCenterTitle("高校详情");
        mCommonToolbar.setLeftImage(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().stopActivity(SchoolDetailMoreInfoActivity.this);
            }
        });

        if (!TextUtils.isEmpty(schoolId))
            mPresenter.getSchoolMoreInfo(schoolId);
    }

    @Override
    public Context getLayoutManagerContext() {
        return this;
    }

    @Override
    public void schoolDetailMoreInfoSuccess(SchoolMoreInfoResp school) {
        List<SchoolMoreInfo> schoolMoreInfos = new ArrayList<>();
        SchoolMoreInfo s1 = new SchoolMoreInfo();
        s1.setTypeKey("aboutXueXiaoGaiKuang");
        s1.setTypeValue(school.getAboutXueXiaoGaiKuang());

        SchoolMoreInfo s2 = new SchoolMoreInfo();
        s2.setTypeKey("aboutYuanXiSheZhi");
        s2.setTypeValue(school.getAboutYuanXiSheZhi());


        SchoolMoreInfo s3 = new SchoolMoreInfo();
        s3.setTypeKey("aboutShiZiLiLiang");
        s3.setTypeValue(school.getAboutShiZiLiLiang());

        SchoolMoreInfo s4 = new SchoolMoreInfo();
        s4.setTypeKey("aboutTiJianBiaoZhun");
        s4.setTypeValue(school.getAboutTiJianBiaoZhun());


        SchoolMoreInfo s5 = new SchoolMoreInfo();
        s5.setTypeKey("aboutShouFeiBiaoZhun");
        s5.setTypeValue(school.getAboutShouFeiBiaoZhun());

        SchoolMoreInfo s6 = new SchoolMoreInfo();
        s6.setTypeKey("aboutJiuYeQingKuang");
        s6.setTypeValue(school.getAboutJiuYeQingKuang());


        SchoolMoreInfo s7 = new SchoolMoreInfo();
        s7.setTypeKey("aboutLuQuGuiZe");
        s7.setTypeValue(school.getAboutLuQuGuiZe());

        SchoolMoreInfo s8 = new SchoolMoreInfo();
        s8.setTypeKey("aboutXueXiaoLingDao");
        s8.setTypeValue(school.getAboutXueXiaoLingDao());


        SchoolMoreInfo s9 = new SchoolMoreInfo();
        s9.setTypeKey("aboutZhongDianXueKe");
        s9.setTypeValue(school.getAboutZhongDianXueKe());

        SchoolMoreInfo s10 = new SchoolMoreInfo();
        s10.setTypeKey("aboutZhaoShengZhengCe");
        s10.setTypeValue(school.getAboutZhaoShengZhengCe());


        SchoolMoreInfo s11 = new SchoolMoreInfo();
        s11.setTypeKey("aboutYiLiuXueKe");
        s11.setTypeValue(school.getAboutYiLiuXueKe());

        schoolMoreInfos.add(s1);
        schoolMoreInfos.add(s2);
        schoolMoreInfos.add(s3);
        schoolMoreInfos.add(s4);
        schoolMoreInfos.add(s5);
        schoolMoreInfos.add(s6);
        schoolMoreInfos.add(s7);
        schoolMoreInfos.add(s8);
        schoolMoreInfos.add(s9);
        schoolMoreInfos.add(s10);
        schoolMoreInfos.add(s11);

        schoolInfoRecycler.setLayoutManager(layoutManager);
        schoolInfoRecycler.setHasFixedSize(true);
        schoolInfoRecycler.setNestedScrollingEnabled(false);
        schoolInfoRecycler.setItemAnimator(new DefaultItemAnimator());
        SchoolMoreInfoAdapter adapter = new SchoolMoreInfoAdapter(schoolMoreInfos);
        schoolInfoRecycler.setAdapter(adapter);
        Logger.d(school.toString());


    }
}
