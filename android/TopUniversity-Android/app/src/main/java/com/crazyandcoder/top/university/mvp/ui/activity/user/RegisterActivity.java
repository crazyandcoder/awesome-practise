package com.crazyandcoder.top.university.mvp.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.integration.EventBusManager;
import com.crazyandcoder.android.lib.base.utils.CrazyUtils;
import com.crazyandcoder.android.lib.base.widget.toast.ToastUtils;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.RegisterSuccess;
import com.crazyandcoder.top.university.bean.User;
import com.crazyandcoder.top.university.bean.UserManager;
import com.crazyandcoder.top.university.di.component.user.DaggerRegisterComponent;
import com.crazyandcoder.top.university.mvp.contract.user.RegisterContract;
import com.crazyandcoder.top.university.mvp.presenter.user.RegisterPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class RegisterActivity extends AbsBaseUIActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.logo_img)
    ImageView logoImg;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.confirm_password_et)
    EditText confirmPasswordEt;
    @BindView(R.id.register_l)
    LinearLayout registerL;
    @BindView(R.id.register_btn)
    Button registerBtn;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerRegisterComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSubmit();
            }
        });
    }

    /**
     * 点击注册信息验证
     */
    public void doSubmit() {
        if (CrazyUtils.isEmpty(usernameEt.getText().toString())) {
            ToastUtils.showToast(this, "请输入用户名");
            return;
        }
        if (CrazyUtils.isEmpty(passwordEt.getText().toString())) {
            ToastUtils.showToast(this, "请输入密码");
            return;
        }
        if (CrazyUtils.isEmpty(confirmPasswordEt.getText().toString())) {
            ToastUtils.showToast(this, "请再次输入密码");
            return;
        }
        if (!confirmPasswordEt.getText().toString().equals(passwordEt.getText().toString())) {
            ToastUtils.showToast(this, "两次密码输入不一致");
            return;
        }
        mPresenter.register(usernameEt.getText().toString(), passwordEt.getText().toString(), confirmPasswordEt.getText().toString());
    }


    @Override
    public void registerSuccess(String name) {
        ToastUtils.showToast(this, "注册成功");
        EventBusManager.getInstance().post(new RegisterSuccess(name));
        finish();
    }
}
