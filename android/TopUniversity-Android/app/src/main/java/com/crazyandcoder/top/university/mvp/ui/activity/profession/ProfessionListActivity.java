package com.crazyandcoder.top.university.mvp.ui.activity.profession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

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
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.Constant;
import com.crazyandcoder.top.university.bean._enum.ACollegeType;
import com.crazyandcoder.top.university.bean._enum.ICollegeType;
import com.crazyandcoder.top.university.bean.req.ProfessionListReq;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;
import com.crazyandcoder.top.university.di.component.profession.DaggerProfessionListComponent;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionListContract;
import com.crazyandcoder.top.university.mvp.presenter.profession.ProfessionListPresenter;
import com.crazyandcoder.top.university.mvp.ui.activity.school.SchoolDetailActivity;
import com.crazyandcoder.top.university.mvp.ui.adapter.ProfessionListAdapter;

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
public class ProfessionListActivity extends AbsBaseUIActivity<ProfessionListPresenter> implements ProfessionListContract.View {
    @BindView(R.id.profession_list_recycler)
    RecyclerView professionListRecycler;

    @Inject
    ProfessionListAdapter professionListAdapter;

    @Inject
    List<ProfessionHotListResp> data;

    @Inject
    RecyclerView.LayoutManager linearLayoutManager;
    private int currentPage = 1;
    private int level3 = 4;//大类


    @ACollegeType
    private int level1 = ICollegeType.bachelorDegree;//本科/专科
    private ProfessionListReq professionListReq;
    private View emptyView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerProfessionListComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_profession_list;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mCommonToolbar.setLeftImage(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().stopActivity(ProfessionListActivity.this);
            }
        });

        mCommonToolbar.getCenterRadioGroup().setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.custom_toolbar_center_left_radiobutton) {
                    level1 = ICollegeType.bachelorDegree;
                } else {
                    level1 = ICollegeType.associateDegree;
                }
                onStartLoadData();
            }
        });

        professionListReq = new ProfessionListReq();
        professionListRecycler.setLayoutManager(linearLayoutManager);
        professionListRecycler.setHasFixedSize(true);
        professionListRecycler.setNestedScrollingEnabled(false);
        professionListRecycler.setFocusableInTouchMode(false);
        professionListRecycler.setItemAnimator(new DefaultItemAnimator());
        professionListRecycler.setAdapter(professionListAdapter);
        emptyView = getLayoutInflater().inflate(R.layout.common_empty_view, (ViewGroup) professionListRecycler.getParent(), false);
        onStartLoadData();

        //加载更多
        professionListAdapter.setOnLoadMoreListener(new AbsCrazyBaseAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                onLoadMoreData();
            }
        }, professionListRecycler);


        //跳转详情
        professionListAdapter.setOnProfessionItemClickListener(new ProfessionListAdapter.OnProfessionItemClickListener() {
            @Override
            public void onClick(int position, ProfessionHotListResp resp) {
                if (professionListAdapter != null && professionListAdapter.getData() != null && position < professionListAdapter.getData().size()) {
                    ProfessionHotListResp itemBean = professionListAdapter.getData().get(position);
                    if (itemBean != null) {
                        Intent intent = new Intent(getActivity(), ProfessionDetailInfoActivity.class);
                        intent.putExtra(Constant.Page.PAGE_KEY, itemBean.getSpecialId());
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
        professionListAdapter.setEnableLoadMore(false);
        onRequestListData();
    }

    /**
     * 加载更多
     */
    private void onLoadMoreData() {
        currentPage++;
        onRequestListData();
    }


    /**
     * 发起请求
     */
    private void onRequestListData() {
        if (professionListReq != null) {
            professionListReq.setLevel1("" + level1);
            professionListReq.setPageNo("" + currentPage);
            professionListReq.setPageSize("" + Constant.Page.PageSize);
            mPresenter.getProfessionList(professionListReq);
        }
    }

    @Override
    public Context getLayoutManagerContext() {
        return this;
    }

    @Override
    public void professionListSuccess(BasePageResult<ProfessionHotListResp> result) {
        //无数据，展示空页面
        if (result != null && currentPage == 1 && (result.getRecords() == null || result.getRecords().size() == 0)) {
            hideLoading();
            if (professionListAdapter != null) {
                professionListAdapter.setNewData(null);
                professionListAdapter.setEmptyView(emptyView);
            }
        } else if (result != null) {
            if (currentPage == 1) {
                professionListAdapter.setEnableLoadMore(true);
                professionListAdapter.setNewData(result.getRecords());
            } else {
                professionListAdapter.addData(result.getRecords());
            }
            //最后返回的数据不足一页
            if (currentPage == result.getPages()) {
                professionListAdapter.loadMoreEnd();
            } else {
                professionListAdapter.loadMoreComplete();
            }
        }
    }
}
