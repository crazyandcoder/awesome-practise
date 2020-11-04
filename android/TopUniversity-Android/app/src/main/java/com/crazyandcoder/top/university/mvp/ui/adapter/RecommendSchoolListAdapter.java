package com.crazyandcoder.top.university.mvp.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.widget.recycler.AbsCrazyBaseAdapter;
import com.crazyandcoder.android.lib.common.widget.recycler.CrazyBaseViewHolder;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import com.sunfusheng.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecommendSchoolListAdapter extends AbsCrazyBaseAdapter<TopSchoolResp, CrazyBaseViewHolder> {

    @Inject
    public RecommendSchoolListAdapter(@Nullable List<TopSchoolResp> data) {
        super(R.layout.item_recommend_school_list, data);
    }

    @Override
    protected void convert(@NonNull CrazyBaseViewHolder helper, TopSchoolResp item) {
        if (item != null) {
            GlideImageView logoImg = (GlideImageView) helper.getView(R.id.recommend_school_img);
            if (!TextUtils.isEmpty(item.getLogo()))
                logoImg.load(item.getLogo());
            helper.setText(R.id.recommend_school_name_tv, "" + item.getName());
        }
    }

}
