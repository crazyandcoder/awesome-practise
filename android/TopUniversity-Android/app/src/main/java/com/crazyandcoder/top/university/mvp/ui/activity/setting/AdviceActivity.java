package com.crazyandcoder.top.university.mvp.ui.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.di.component.setting.DaggerAdviceComponent;
import com.crazyandcoder.top.university.mvp.contract.setting.AdviceContract;
import com.crazyandcoder.top.university.mvp.presenter.setting.AdvicePresenter;
import com.crazyandcoder.top.university.utils.QQUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class AdviceActivity extends AbsBaseUIActivity<AdvicePresenter> implements AdviceContract.View {

    @BindView(R.id.content_tv)
    TextView contentTv;
    @BindView(R.id.join_qqgroup_btn)
    TextView joinQqgroupBtn;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerAdviceComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_advice;
    }


    @Override
    public boolean showToolbar() {
        return true;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        joinQqgroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QQUtils.joinQQGroup(AdviceActivity.this);
            }
        });


        mCommonToolbar.setCenterTitle("意见反馈");
        mCommonToolbar.setLeftImage(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
