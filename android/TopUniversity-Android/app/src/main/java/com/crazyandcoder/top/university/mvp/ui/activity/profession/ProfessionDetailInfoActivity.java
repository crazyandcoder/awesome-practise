package com.crazyandcoder.top.university.mvp.ui.activity.profession;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.base.base.AbsBaseUIActivity;
import com.crazyandcoder.android.lib.base.di.component.AppComponent;
import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.android.lib.base.utils.JsonUtil;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.Constant;
import com.crazyandcoder.top.university.bean.resp.ProfessionDetailInfoResp;
import com.crazyandcoder.top.university.bean.resp.ProfessionImpress;
import com.crazyandcoder.top.university.di.component.profession.DaggerProfessionDetailInfoComponent;
import com.crazyandcoder.top.university.mvp.contract.profession.ProfessionDetailInfoContract;
import com.crazyandcoder.top.university.mvp.presenter.profession.ProfessionDetailInfoPresenter;
import com.crazyandcoder.top.university.mvp.ui.activity.school.SchoolDetailMoreInfoActivity;
import com.crazyandcoder.top.university.mvp.ui.adapter.ProfessionImpressListAdapter;
import com.orhanobut.logger.Logger;
import com.zzhoujay.richtext.RichText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * ================================================
 *
 * @Author: crazyandcoder
 * @Version: 1.0
 * ================================================
 */
public class ProfessionDetailInfoActivity extends AbsBaseUIActivity<ProfessionDetailInfoPresenter> implements ProfessionDetailInfoContract.View {

    @BindView(R.id.recommend_profession_name_tv)
    TextView recommendProfessionNameTv;
    @BindView(R.id.item_recommend_profession_name_tv)
    TextView itemRecommendProfessionNameTv;
    @BindView(R.id.item_recommend_profession_level2_tv)
    TextView itemRecommendProfessionLevel2Tv;
    @BindView(R.id.item_recommend_profession_level1_tv)
    TextView itemRecommendProfessionLevel1Tv;
    @BindView(R.id.item_recommend_profession_level3_tv)
    TextView itemRecommendProfessionLevel3Tv;
    @BindView(R.id.item_recommend_profession_degree_tv)
    TextView itemRecommendProfessionDegreeTv;
    @BindView(R.id.item_recommend_profession_limit_tv)
    TextView itemRecommendProfessionLimitTv;
    @BindView(R.id.impress_list_recycler)
    RecyclerView impressListRecycler;
    @BindView(R.id.profession_info_content_tv)
    TextView professionInfoContentTv;
    @BindView(R.id.profession_course_content_tv)
    TextView professionCourseContentTv;
    @BindView(R.id.profession_job_content_tv)
    TextView professionJobContentTv;
    @BindView(R.id.school_jianjie_content_more_tv)
    TextView schoolJianjieContentMoreTv;
    @BindView(R.id.profession_man)
    TextView professionMan;
    @BindView(R.id.profession_woman)
    TextView professionWoman;
    private String professionId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        //如找不到该类,请编译一下项目
        DaggerProfessionDetailInfoComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_profession_detail_info;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mCommonToolbar.setCenterTitle("专业详情");
        mCommonToolbar.setLeftImage(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().stopActivity(ProfessionDetailInfoActivity.this);
            }
        });
        professionId = this.getIntent().getStringExtra(Constant.Page.PAGE_KEY);
        if (!TextUtils.isEmpty(professionId)) {
            mPresenter.getProfessionDetailInfo(professionId);
        }
    }

    @Override
    public void ProfessionDetailInfoSuccess(ProfessionDetailInfoResp profession) {
        mCommonToolbar.setCenterTitle("" + profession.getName());
        itemRecommendProfessionNameTv.setText("" + profession.getName());
        itemRecommendProfessionLevel1Tv.setText("" + profession.getLevel1_name());
        itemRecommendProfessionLevel2Tv.setText("" + profession.getType_detail());
        itemRecommendProfessionLevel3Tv.setText("" + profession.getType_detail());
        itemRecommendProfessionDegreeTv.setText("" + profession.getDegree());
        itemRecommendProfessionLimitTv.setText("" + profession.getLimit_year());

        RichText.from(profession.getIs_what()).into(professionInfoContentTv);
        RichText.from(profession.getLearn_what()).into(professionCourseContentTv);
        RichText.from(profession.getJob()).into(professionJobContentTv);
        String rate = profession.getRate();
        if (!TextUtils.isEmpty(rate) && rate.contains(":")) {
            String[] rates = rate.split(":");
            if (rates.length == 2) {
                professionMan.setText("" + rates[0] + "%");
                professionWoman.setText("" + rates[1] + "%");
            }
        }
        if (!TextUtils.isEmpty(profession.getImpress())) {
            List<ProfessionImpress> impressList = JsonUtil.getListData(profession.getImpress(), ProfessionImpress.class);
            if (impressList != null && impressList.size() > 0) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(RecyclerView.HORIZONTAL);
                impressListRecycler.setLayoutManager(layoutManager);
                impressListRecycler.setHasFixedSize(true);
                impressListRecycler.setNestedScrollingEnabled(false);
                impressListRecycler.setFocusableInTouchMode(false);
                impressListRecycler.setItemAnimator(new DefaultItemAnimator());
                ProfessionImpressListAdapter adapter = new ProfessionImpressListAdapter(impressList);
                impressListRecycler.setAdapter(adapter);
            }
        }


    }

    @Override
    public void ProfessionDetailInfoFailure() {

    }

}
