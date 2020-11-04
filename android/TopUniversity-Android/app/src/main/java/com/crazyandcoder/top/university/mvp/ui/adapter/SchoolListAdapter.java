package com.crazyandcoder.top.university.mvp.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.widget.recycler.AbsCrazyBaseAdapter;
import com.crazyandcoder.android.lib.common.widget.recycler.CrazyBaseViewHolder;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.School;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.ihidea.multilinechooselib.MultiLineChooseLayout;
import com.sunfusheng.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SchoolListAdapter extends AbsCrazyBaseAdapter<TopSchoolResp, CrazyBaseViewHolder> {

    @Inject
    public SchoolListAdapter(@Nullable List<TopSchoolResp> data) {
        super(R.layout.item_school_list, data);
    }

    @Override
    protected void convert(@NonNull CrazyBaseViewHolder helper, TopSchoolResp item) {
        if (item != null) {
            GlideImageView logoImg = (GlideImageView) helper.getView(R.id.school_img);
            if (!TextUtils.isEmpty(item.getLogo()))
                logoImg.load(item.getLogo());

            helper.setText(R.id.school_name_tv, "" + item.getName());
            helper.setText(R.id.school_role_tv, "" + item.getLevelName());
            if (!TextUtils.isEmpty(getSchoolType(item))) {
                ((TextView) (helper.getView(R.id.school_type_tv))).setVisibility(View.VISIBLE);
                ((TextView) (helper.getView(R.id.school_type_tv))).setText(getSchoolType(item));
            } else {
                ((TextView) (helper.getView(R.id.school_type_tv))).setVisibility(View.GONE);
            }
            helper.setText(R.id.school_location_tv, "" + item.getAddress());
        }
    }

    /**
     * 设置学校tag
     *
     * @param schoolTagLayout
     * @param item
     */
    private void setSchoolTag(MultiLineChooseLayout schoolTagLayout, TopSchoolResp item) {
        List<String> schoolTagList = new ArrayList<>();
        if (!TextUtils.isEmpty(item.getNatureName())) {
            schoolTagList.add(item.getNatureName());
        }
        if (!TextUtils.isEmpty(item.getF211()) && item.getF211().equals("1")) {
            schoolTagList.add("211");
        }

        if (!TextUtils.isEmpty(item.getF985()) && item.getF985().equals("1")) {
            schoolTagList.add("985");
        }
        if (schoolTagList != null && schoolTagList.size() > 0) {
            schoolTagLayout.setList(schoolTagList);
        }
    }

    private String getSchoolType(TopSchoolResp item) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(item.getF985()) && item.getF985().equals("1")) {
            sb.append("985 ");
        }
        if (!TextUtils.isEmpty(item.getF211()) && item.getF211().equals("1")) {
            sb.append("211");
        }
        return sb.toString();
    }
}
