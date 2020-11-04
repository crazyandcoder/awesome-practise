package com.crazyandcoder.top.university.mvp.ui.activity.school;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.android.lib.base.utils.JsonUtil;
import com.crazyandcoder.android.lib.common.widget.banner.listener.OnBannerListener;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.Constant;
import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.req.SchoolDetailInfoReq;
import com.crazyandcoder.top.university.bean.resp.SchoolImage;
import com.crazyandcoder.top.university.di.component.school.DaggerSchoolDetailComponent;
import com.crazyandcoder.top.university.mvp.contract.school.SchoolDetailContract;
import com.crazyandcoder.top.university.mvp.presenter.school.SchoolDetailPresenter;
import com.crazyandcoder.top.university.mvp.ui.adapter.SchoolImagesAdapter;
import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import com.orhanobut.logger.Logger;
import com.sunfusheng.GlideImageView;
import com.crazyandcoder.android.lib.common.widget.banner.Banner;
import com.zzhoujay.richtext.RichText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class SchoolDetailActivity extends AbsBaseUIActivity<SchoolDetailPresenter> implements SchoolDetailContract.View {

    @BindView(R.id.school_img)
    GlideImageView schoolImg;
    @BindView(R.id.school_name_tv)
    TextView schoolNameTv;
    @BindView(R.id.school_role_tv)
    TextView schoolRoleTv;
    @BindView(R.id.school_type_tv)
    TextView schoolTypeTv;
    @BindView(R.id.school_location_tv)
    TextView schoolLocationTv;
    @BindView(R.id.boshi_img)
    ImageView boshiImg;
    @BindView(R.id.boshi_tv)
    TextView boshiTv;
    @BindView(R.id.shuoshi_img)
    ImageView shuoshiImg;
    @BindView(R.id.shuoshi_tv)
    TextView shuoshiTv;
    @BindView(R.id.shcool_phone_tv)
    TextView shcoolPhoneTv;
    @BindView(R.id.shcool_website_tv)
    TextView shcoolWebsiteTv;
    @BindView(R.id.school_tag_flowLayout)
    MultiLineChooseLayout schoolTagFlowLayout;
    @BindView(R.id.school_jianjie_content_tv)
    TextView schoolJianjieContentTv;
    @BindView(R.id.school_jianjie_content_more_tv)
    TextView schoolJianjieContentMoreTv;
    @BindView(R.id.school_image_banner)
    Banner schoolImageBanner;
    @BindView(R.id.school_image_l)
    LinearLayout schoolImageL;
    private String schoolId = "";

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerSchoolDetailComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_school_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mCommonToolbar.setCenterTitle("高校详情");
        mCommonToolbar.setLeftImage(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().stopActivity(SchoolDetailActivity.this);
            }
        });
        schoolId = this.getIntent().getStringExtra(Constant.Page.PAGE_KEY);
        mPresenter.getSchoolDetailInfo(new SchoolDetailInfoReq(schoolId));

        //学校图集
        schoolImageBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {

            }
        });

        //查看更多
        schoolJianjieContentMoreTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SchoolDetailActivity.this, SchoolDetailMoreInfoActivity.class);
                intent.putExtra(Constant.Page.PAGE_KEY, schoolId);
                AppManager.getAppManager().startActivity(intent);
            }
        });
    }

    @Override
    public boolean showToolbar() {
        return true;
    }

    @Override
    public void schoolDetailSuccess(School school) {
        if (school != null) {
            mCommonToolbar.setCenterTitle("" + school.getSchoolName());
            setSchoolTag(school);
            schoolJianjieContentTv.setText("" + school.getSchoolContent() + "...");
            if (!TextUtils.isEmpty(school.getSchoolShort())) {
                schoolRoleTv.setVisibility(View.VISIBLE);
                schoolRoleTv.setText("" + school.getSchoolShort());
            } else {
                schoolRoleTv.setVisibility(View.GONE);
            }
            schoolImg.load(school.getSchoolLogo());
            schoolNameTv.setText("" + school.getSchoolName());
            schoolLocationTv.setText("" + school.getSchoolAddress());
            schoolTypeTv.setText("" + school.getSchoolType());
            shcoolWebsiteTv.setText("" + school.getSchoolSite());
            shcoolPhoneTv.setText("" + school.getSchoolPhone());
            shuoshiTv.setText("" + school.getSchoolMasterNum());
            boshiTv.setText("" + school.getSchoolDoctorNum());

            setSchoolImages(school);
        }
    }

    /**
     * 设置图集
     *
     * @param school
     */
    private void setSchoolImages(School school) {
        if (!TextUtils.isEmpty(school.getSchoolImages())) {
            schoolImageL.setVisibility(View.VISIBLE);
            try {
                List<SchoolImage> imageList = new ArrayList<>();
                imageList = JsonUtil.getResultArrayListData(school.getSchoolImages(), SchoolImage.class);
                SchoolImagesAdapter schoolImagesAdapter = new SchoolImagesAdapter(imageList);
                schoolImageBanner.setAdapter(schoolImagesAdapter);
                schoolImageBanner.removeIndicator();
                Logger.d(imageList);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            schoolImageL.setVisibility(View.GONE);

        }

    }

    /**
     * 设置学校标签
     *
     * @param item
     */
    private void setSchoolTag(School item) {
        List<String> schoolTagList = new ArrayList<>();

        //隶属于教育部
        if (!TextUtils.isEmpty(item.getSchoolBelong())) {
            schoolTagList.add(item.getSchoolBelong());
        }

        //普通本科
        if (!TextUtils.isEmpty(item.getSchoolLevelName())) {
            schoolTagList.add(item.getSchoolLevelName());
        }

//        综合类
        if (!TextUtils.isEmpty(item.getSchoolTypeName())) {
            schoolTagList.add(item.getSchoolTypeName());
        }
        //公办
        if (!TextUtils.isEmpty(item.getSchoolNatureName())) {
            schoolTagList.add(item.getSchoolNatureName());
        }

        //211
        if (!TextUtils.isEmpty(item.getSchool211()) && item.getSchool211().equals("1")) {
            schoolTagList.add("211");
        }

        //985
        if (!TextUtils.isEmpty(item.getSchool985()) && item.getSchool985().equals("1")) {
            schoolTagList.add("985");
        }

        if (schoolTagList != null && schoolTagList.size() > 0) {
            schoolTagFlowLayout.setList(schoolTagList);
            schoolTagFlowLayout.onlyShow();
        }
    }


}
