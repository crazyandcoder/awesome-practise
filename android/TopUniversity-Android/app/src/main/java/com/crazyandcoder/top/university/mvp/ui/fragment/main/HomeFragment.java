package com.crazyandcoder.top.university.mvp.ui.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.crazyandcoder.android.lib.base.base.AbsCrazyBaseFragment;
import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.android.lib.common.widget.banner.Banner;
import com.crazyandcoder.android.lib.common.widget.recycler.AbsCrazyBaseAdapter;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.Constant;
import com.crazyandcoder.top.university.bean.req.ProfessionHotListReq;
import com.crazyandcoder.top.university.bean.req.SchoolHotListReq;
import com.crazyandcoder.top.university.bean.resp.BannerInfoResp;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.di.component.main.DaggerHomeComponent;
import com.crazyandcoder.top.university.mvp.contract.main.HomeContract;
import com.crazyandcoder.top.university.mvp.presenter.main.HomePresenter;
import com.crazyandcoder.top.university.mvp.ui.activity.profession.ProfessionHotListActivity;
import com.crazyandcoder.top.university.mvp.ui.activity.profession.ProfessionListActivity;
import com.crazyandcoder.top.university.mvp.ui.activity.school.SchoolDetailActivity;
import com.crazyandcoder.top.university.mvp.ui.activity.profession.ProfessionDetailInfoActivity;
import com.crazyandcoder.top.university.mvp.ui.activity.school.SchoolListActivity;
import com.crazyandcoder.top.university.mvp.ui.adapter.HomeBannerImageAdapter;
import com.crazyandcoder.top.university.mvp.ui.adapter.RecommendProfessionHotListAdapter;
import com.crazyandcoder.top.university.mvp.ui.adapter.RecommendSchoolListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class HomeFragment extends AbsCrazyBaseFragment<HomePresenter> implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.school_list_recycler)
    RecyclerView schoolListRecycler;

    @Inject
    RecommendSchoolListAdapter schoolListAdapter;


    @BindView(R.id.img_catagory_school)
    ImageView imgCatagorySchool;
    @BindView(R.id.tv_catagory_school)
    TextView tvCatagorySchool;
    @BindView(R.id.catagory_school_rl)
    RelativeLayout catagorySchoolRl;
    @BindView(R.id.img_catagory_special)
    ImageView imgCatagorySpecial;
    @BindView(R.id.tv_catagory_special)
    TextView tvCatagorySpecial;
    @BindView(R.id.catagory_special_rl)
    RelativeLayout catagorySpecialRl;
    @BindView(R.id.profession_banner)
    Banner professionBanner;
    @BindView(R.id.school_swipe_layout)
    SwipeRefreshLayout schoolSwipeLayout;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.more_hot_school_rl)
    RelativeLayout moreHotSchoolRl;
    @BindView(R.id.more_hot_profession_rl)
    RelativeLayout moreHotProfessionRl;

    private LinearLayoutManager linearLayoutManager;

    @Inject
    List<TopSchoolResp> list;

    private int currentPage = 1;
    private boolean isRefresh = true;
    private SchoolHotListReq schoolListReq = new SchoolHotListReq();
    private ProfessionHotListReq professionHotListReq = new ProfessionHotListReq();

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public View initLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        onStartLoadData();
        banner.setAdapter(new HomeBannerImageAdapter(BannerInfoResp.getTestData()));
        banner.isAutoLoop(false);
        banner.removeIndicator();
        schoolSwipeLayout.setColorSchemeColors(Color.parseColor("#3496FA"));
        schoolSwipeLayout.setOnRefreshListener(this);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        schoolListRecycler.setLayoutManager(linearLayoutManager);
        schoolListRecycler.setHasFixedSize(true);
        schoolListRecycler.setNestedScrollingEnabled(false);
        schoolListRecycler.setFocusableInTouchMode(false);
        schoolListRecycler.setItemAnimator(new DefaultItemAnimator());
        schoolListRecycler.setAdapter(schoolListAdapter);

        //跳转详情
        schoolListAdapter.setOnItemClickListener(new AbsCrazyBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AbsCrazyBaseAdapter adapter, View view, int position) {
                if (schoolListAdapter != null && schoolListAdapter.getData() != null && position < schoolListAdapter.getData().size()) {
                    TopSchoolResp itemBean = schoolListAdapter.getData().get(position);
                    if (itemBean != null) {
                        Intent intent = new Intent();
                        intent.putExtra(Constant.Page.PAGE_KEY, itemBean.getSchoolId());
                        intent.setClass(getActivity(), SchoolDetailActivity.class);
                        AppManager.getAppManager().startActivity(intent);
                    }
                }
            }
        });


        //高校列表
        catagorySchoolRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().startActivity(SchoolListActivity.class);
            }
        });

        //专业列表
        catagorySpecialRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().startActivity(ProfessionListActivity.class);
            }
        });

        //更多的热门专业
        moreHotProfessionRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().startActivity(ProfessionHotListActivity.class);
            }
        });

        //更多的热门专业
        moreHotSchoolRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SchoolListActivity.class);
                AppManager.getAppManager().startActivity(intent);
            }
        });

    }


    /**
     * 下拉刷新
     */
    private void onStartLoadData() {
        currentPage = 1;
        isRefresh = true;
        //这里的作用是防止下拉刷新的时候还可以上拉加载
        schoolListAdapter.setEnableLoadMore(false);
        onRequestListData();
    }


    /**
     * 发起请求
     */
    private void onRequestListData() {
        //热门高校列表请求
        if (schoolListReq != null) {
            schoolListReq.setSchoolType("6000");
            schoolListReq.setPageSize("" + Constant.Page.PageSize);
            schoolListReq.setPageNo("" + currentPage);
            mPresenter.getSchoolHotList(schoolListReq);

        }

        //热门专业列表
        if (professionHotListReq != null) {
            professionHotListReq.setPageNo("1");
            professionHotListReq.setLevel1("1");
            professionHotListReq.setPageSize("" + Constant.Page.PageSize);
            mPresenter.getProfessionHotList(professionHotListReq);
        }
    }


    @Override
    public void showLoading() {
        schoolSwipeLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        schoolSwipeLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public Context getLayoutManagerContext() {
        return getActivity();
    }

    @Override
    public void schoolListSuccess(BasePageResult<TopSchoolResp> result) {
        schoolListAdapter.setNewData(result.getRecords());
        schoolListRecycler.setAdapter(schoolListAdapter);
        schoolListAdapter.setEnableLoadMore(false);
        hideLoading();
    }

    @Override
    public void schoolListFailure() {
        schoolSwipeLayout.setRefreshing(false);
        schoolListAdapter.setEnableLoadMore(false);
        hideLoading();
    }

    @Override
    public void ProfessionHotListSuccess(BasePageResult<ProfessionHotListResp> prfession) {
        hideLoading();
        RecommendProfessionHotListAdapter itemAdapter = new RecommendProfessionHotListAdapter(prfession.getRecords());
        professionBanner.setAdapter(itemAdapter);
        professionBanner.removeIndicator();
        itemAdapter.setOnItemClickListener(new RecommendProfessionHotListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, ProfessionHotListResp resp) {
                Intent intent = new Intent(getActivity(), ProfessionDetailInfoActivity.class);
                intent.putExtra(Constant.Page.PAGE_KEY, resp.getSpecialId());
                AppManager.getAppManager().startActivity(intent);
            }
        });

    }

    @Override
    public void ProfessionHotListFailure() {
        hideLoading();
    }

    @Override
    public void onRefresh() {
        onStartLoadData();
    }
}
