package com.crazyandcoder.top.university.mvp.ui.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.android.lib.base.utils.CrazyUtils;
import com.crazyandcoder.android.lib.common.widget.dialog.CommonAlertDialog;
import com.crazyandcoder.android.lib.common.widget.dialog.ICrazyBaseDialogListener;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.UserManager;
import com.crazyandcoder.top.university.di.component.setting.DaggerSettingComponent;
import com.crazyandcoder.top.university.mvp.contract.setting.SettingContract;
import com.crazyandcoder.top.university.mvp.presenter.setting.SettingPresenter;
import com.crazyandcoder.top.university.mvp.ui.activity.user.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class SettingActivity extends AbsBaseUIActivity<SettingPresenter> implements SettingContract.View {


    @BindView(R.id.login_out_btn)
    Button loginOutBtn;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerSettingComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mCommonToolbar.setCenterTitle("设置");
        mCommonToolbar.setLeftImage(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        loginOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginStatus();
            }
        });
    }

    @Override
    public boolean showToolbar() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserManager.getInstance().login()) {
            loginOutBtn.setText("退出");
        } else {
            loginOutBtn.setText("登录");
        }
    }

    private void loginStatus() {
        if (UserManager.getInstance().login()) {
            new CommonAlertDialog.Builder().with(SettingActivity.this)
                    .setTitle("温馨提示")
                    .setContent("是否需要确认退出？")
                    .setLeftText("取消")
                    .setRightText("退出")
                    .setOnDialogListener(new ICrazyBaseDialogListener() {
                        @Override
                        public void cancel() {
                        }

                        @Override
                        public void confirm(Object o) {
                            AppManager.getAppManager().killAll();
                            UserManager.getInstance().logOut(SettingActivity.this);
                            Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }).build().show();

        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
