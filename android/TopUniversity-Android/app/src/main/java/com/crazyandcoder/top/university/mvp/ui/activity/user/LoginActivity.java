package com.crazyandcoder.top.university.mvp.ui.activity.user;

import android.content.Intent;
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
import com.crazyandcoder.android.lib.base.utils.CrazyUtils;
import com.crazyandcoder.android.lib.base.widget.toast.ToastUtils;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.RegisterSuccess;
import com.crazyandcoder.top.university.bean.User;
import com.crazyandcoder.top.university.bean.UserManager;
import com.crazyandcoder.top.university.di.component.user.DaggerLoginComponent;
import com.crazyandcoder.top.university.mvp.contract.user.LoginContract;
import com.crazyandcoder.top.university.mvp.presenter.user.LoginPresenter;
import com.crazyandcoder.top.university.mvp.ui.activity.setting.AdviceActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class LoginActivity extends AbsBaseUIActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.logo_img)
    ImageView logoImg;
    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.username_et)
    EditText usernameEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.login_l)
    LinearLayout loginL;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.forget_password_tv)
    TextView forgetPasswordTv;
    @BindView(R.id.register_tv)
    TextView registerTv;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSubmit();
            }
        });
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        forgetPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, AdviceActivity.class));
            }
        });
    }

    private void doSubmit() {
        if (CrazyUtils.isEmpty(usernameEt.getText().toString())) {
            ToastUtils.showToast(this, "请输入用户名");
            return;
        }
        if (CrazyUtils.isEmpty(passwordEt.getText().toString())) {
            ToastUtils.showToast(this, "请输入密码");
            return;
        }


        mPresenter.login(usernameEt.getText().toString(), passwordEt.getText().toString());

    }


    @Override
    public void loginSuccess(User user) {
        UserManager.getInstance().setUserId(user.getUserId(), user.getUserName());
        ToastUtils.showToast(this, "登录成功");
        finish();
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    /**
     * 注册成功后，关闭注册页面，回到登录页面进行登录，并填入注册用户名
     *
     * @param event
     */
    @Subscribe
    public void OnRegisterSuccessEvent(RegisterSuccess event) {
        if (!CrazyUtils.isEmpty(event)) {
            usernameEt.setText("" + event.getUserName());
        }
    }


}
