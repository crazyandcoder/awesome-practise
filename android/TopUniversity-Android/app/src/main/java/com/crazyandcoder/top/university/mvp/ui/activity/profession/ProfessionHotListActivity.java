package com.crazyandcoder.top.university.mvp.ui.activity.profession;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.Constant;
import com.crazyandcoder.top.university.bean.req.ProfessionHotListReq;
import com.crazyandcoder.top.university.di.component.profession.DaggerProfessionHotListComponent;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionHotListContract;
import com.crazyandcoder.top.university.mvp.presenter.profession.ProfessionHotListPresenter;
import com.crazyandcoder.top.university.mvp.ui.adapter.RecommendProfessionHotListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class ProfessionHotListActivity extends AbsBaseUIActivity<ProfessionHotListPresenter> implements ProfessionHotListContract.View {

    RecommendProfessionHotListAdapter professionListAdapter;
    @BindView(R.id.hot_profession_list_recycler)
    RecyclerView hotProfessionListRecycler;
    @BindView(R.id.hot_profession_swipe_layout)
    SwipeRefreshLayout hotProfessionSwipeLayout;
    private int currentPage = 1;
    private boolean isRefresh = true;
    private ProfessionHotListReq professionHotListReq = new ProfessionHotListReq();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerProfessionHotListComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_profession_hot_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 下拉刷新
     */
    private void onStartLoadData() {
        currentPage = 1;
        isRefresh = true;
        //这里的作用是防止下拉刷新的时候还可以上拉加载
//        professionListAdapter.setEnableLoadMore(false);
        onRequestListData();
    }

    @Override
    public void showLoading() {
//        schoolSwipeLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
//        schoolSwipeLayout.setRefreshing(false);
    }


    /**
     * 发起请求
     */
    private void onRequestListData() {

        //热门专业列表
        if (professionHotListReq != null) {
            professionHotListReq.setPageNo("1");
            professionHotListReq.setLevel1("1");
            professionHotListReq.setPageSize("" + Constant.Page.PageSize);
//            mPresenter.getProfessionHotList(professionHotListReq);
        }
    }


}
