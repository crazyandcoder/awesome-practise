package com.crazyandcoder.top.university.mvp.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.base.AbsCrazyBaseFragment;
import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.android.lib.base.utils.CrazyUtils;
import com.crazyandcoder.android.lib.base.widget.toast.ToastUtils;
import com.crazyandcoder.android.lib.common.widget.dialog.CommonAlertDialog;
import com.crazyandcoder.android.lib.common.widget.dialog.ICrazyBaseDialogListener;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.UpdateCoreSuper;
import com.crazyandcoder.top.university.bean.UserManager;
import com.crazyandcoder.top.university.di.component.main.DaggerMyComponent;
import com.crazyandcoder.top.university.mvp.contract.main.MyContract;
import com.crazyandcoder.top.university.mvp.presenter.main.MyPresenter;
import com.crazyandcoder.top.university.mvp.ui.activity.setting.AboutActivity;
import com.crazyandcoder.top.university.mvp.ui.activity.setting.AdviceActivity;
import com.crazyandcoder.top.university.mvp.ui.activity.setting.SettingActivity;
import com.crazyandcoder.top.university.mvp.ui.activity.user.LoginActivity;

import butterknife.BindView;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class MyFragment extends AbsCrazyBaseFragment<MyPresenter> implements MyContract.View {

    @BindView(R.id.info_rl)
    FrameLayout infoRl;
    @BindView(R.id.fav_l)
    LinearLayout favL;
    @BindView(R.id.about_l)
    LinearLayout aboutL;
    @BindView(R.id.feedback_l)
    LinearLayout feedbackL;
    @BindView(R.id.update_l)
    LinearLayout updateL;
    @BindView(R.id.setting_l)
    LinearLayout settingL;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.name_l)
    LinearLayout nameL;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerMyComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public View initLayout(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


        infoRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UserManager.getInstance().login()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });


        favL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UserManager.getInstance().login()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });

        nameL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!UserManager.getInstance().login()) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });


        aboutL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });


        feedbackL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AdviceActivity.class));
            }
        });


        settingL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });


        updateL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.checkUpdate();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        if (UserManager.getInstance().login()) {
            nameTv.setText("你好," + UserManager.getInstance().getUserName());
        } else {
            nameTv.setText("登录");
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void hasUpdate(UpdateCoreSuper update) {
        UpdateCoreSuper mUpdateApp = update;
        String updateLog = mUpdateApp.getUpdateDesc();
        new CommonAlertDialog.Builder().with(getActivity())
                .setTitle("更新提示")
                .setContent(updateLog)
                .setLeftText("取消")
                .setRightText("更新")
                .setOnDialogListener(new ICrazyBaseDialogListener() {
                    @Override
                    public void cancel() {
                    }

                    @Override
                    public void confirm(Object o) {
                        ToastUtils.showToast(getContext(), "正在后台下载中...");

                    }
                }).build().show();

    }

    @Override
    public void updateError() {

    }
}
