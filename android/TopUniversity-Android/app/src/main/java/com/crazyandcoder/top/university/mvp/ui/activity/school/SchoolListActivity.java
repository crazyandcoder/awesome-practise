package com.crazyandcoder.top.university.mvp.ui.activity.school;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.bean.BasePageResult;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.android.lib.base.widget.toast.ToastUtils;
import com.crazyandcoder.android.lib.common.widget.recycler.AbsCrazyBaseAdapter;
import com.crazyandcoder.top.university.bean.Constant;
import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.req.SchoolListReq;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.di.component.school.DaggerSchoolListComponent;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolListContract;
import com.crazyandcoder.top.university.mvp.presenter.school.SchoolListPresenter;

import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.mvp.ui.adapter.SchoolListAdapter;
import com.crazyandcoder.top.university.mvp.ui.dialog.SchoolSelectDialog;

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
public class SchoolListActivity extends AbsBaseUIActivity<SchoolListPresenter> implements SchoolListContract.View {


    @BindView(R.id.school_list_recycler)
    RecyclerView schoolListRecycler;

    @Inject
    SchoolListAdapter schoolListAdapter;

    @Inject
    List<TopSchoolResp> data;

    @Inject
    RecyclerView.LayoutManager linearLayoutManager;
    private int currentPage = 1;
    private SchoolListReq schoolListReq;
    private View emptyView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerSchoolListComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_school_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        mCommonToolbar.setCenterTitle("查大学");
        mCommonToolbar.setLeftImage(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().stopActivity(SchoolListActivity.this);
            }
        });
        mCommonToolbar.setRightImage(R.drawable.ic_select, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SchoolSelectDialog dialog = new SchoolSelectDialog(SchoolListActivity.this, schoolListReq, new SchoolSelectDialog.OnSelectListener() {
                    @Override
                    public void onSelect(SchoolListReq schoolListReq) {
                        SchoolListActivity.this.schoolListReq = schoolListReq;
                        onStartLoadData();
                    }
                });
                dialog.show();

            }
        });

        schoolListReq = new SchoolListReq();


        schoolListRecycler.setLayoutManager(linearLayoutManager);
        schoolListRecycler.setHasFixedSize(true);
        schoolListRecycler.setNestedScrollingEnabled(false);
        schoolListRecycler.setFocusableInTouchMode(false);
        schoolListRecycler.setItemAnimator(new DefaultItemAnimator());
        schoolListRecycler.setAdapter(schoolListAdapter);
        emptyView = getLayoutInflater().inflate(R.layout.common_empty_view, (ViewGroup) schoolListRecycler.getParent(), false);
        onStartLoadData();

        //加载更多
        schoolListAdapter.setOnLoadMoreListener(new AbsCrazyBaseAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMoreData();
            }
        }, schoolListRecycler);


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
    }

    @Override
    public boolean showToolbar() {
        return true;
    }

    /**
     * 下拉刷新
     */
    private void onStartLoadData() {
        currentPage = 1;
        schoolListAdapter.setEnableLoadMore(false);
        onRequestListData();
    }

    /**
     * 加载更多
     */
    private void onLoadMoreData() {
        currentPage++;
        //员工列表请求
        onRequestListData();
    }


    /**
     * 发起请求
     */
    private void onRequestListData() {
        //员工列表请求
        if (schoolListReq != null) {
            schoolListReq.setPageNo("" + currentPage);
            schoolListReq.setPageSize("" + Constant.Page.PageSize);
            mPresenter.getSchoolList(currentPage == 1 ? true : false, schoolListReq);
        }
    }

    @Override
    public Context getLayoutManagerContext() {
        return this;
    }

    @Override
    public void schoolListSuccess(BasePageResult<TopSchoolResp> result) {
        //无数据，展示空页面
        if (result != null && currentPage == 1 && (result.getRecords() == null || result.getRecords().size() == 0)) {
            hideLoading();
            if (schoolListAdapter != null) {
                schoolListAdapter.setNewData(null);
                schoolListAdapter.setEmptyView(emptyView);
            }
        } else if (result != null) {
            if (currentPage == 1) {
                schoolListAdapter.setEnableLoadMore(true);
                schoolListAdapter.setNewData(result.getRecords());
            } else {
                schoolListAdapter.addData(result.getRecords());
                //加上下面这一行会导致重新回到第一页数据，但是在ScrollView中，不加的话会导致显示不全
//                schoolListRecycler.setAdapter(schoolListAdapter);

            }
            //最后返回的数据不足一页
            if (currentPage == result.getPages()) {
                schoolListAdapter.loadMoreEnd();
            } else {
                schoolListAdapter.loadMoreComplete();
            }
        }
    }
}
