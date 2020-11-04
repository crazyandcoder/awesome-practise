package com.crazyandcoder.top.university.mvp.ui.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;

import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.top.university.di.component.setting.DaggerAboutComponent;
import com.crazyandcoder.top.university.mvp.contract.setting.AboutContract;
import com.crazyandcoder.top.university.mvp.presenter.setting.AboutPresenter;

import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.mvp.ui.activity.school.SchoolDetailActivity;
import com.crazyandcoder.top.university.utils.QQUtils;

import butterknife.BindView;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class AboutActivity extends AbsBaseUIActivity<AboutPresenter> implements AboutContract.View {
    @BindView(R.id.tree_hole_user_avatar_img)
    ImageView treeHoleUserAvatarImg;
    @BindView(R.id.tree_hole_user_nick_name_tv)
    TextView treeHoleUserNickNameTv;
    @BindView(R.id.home_my_head_l)
    RelativeLayout homeMyHeadL;
    @BindView(R.id.setting_user_about_l)
    LinearLayout settingUserAboutL;
    @BindView(R.id.setting_user_protocol_l)
    LinearLayout settingUserProtocolL;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerAboutComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mCommonToolbar.setCenterTitle("关于我们");
        mCommonToolbar.setLeftImage(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        settingUserAboutL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QQUtils.joinQQGroup(AboutActivity.this);
            }
        });
    }
}
