package com.crazyandcoder.top.university.mvp.ui.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.widget.recycler.AbsCrazyBaseAdapter;
import com.crazyandcoder.android.lib.common.widget.recycler.CrazyBaseViewHolder;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.resp.ProfessionImpress;
import com.crazyandcoder.top.university.bean.resp.TopSchoolResp;
import com.crazyandcoder.top.university.utils.ImageUtils;
import com.sunfusheng.GlideImageView;

import java.util.List;

import javax.inject.Inject;

public class ProfessionImpressListAdapter extends AbsCrazyBaseAdapter<ProfessionImpress, CrazyBaseViewHolder> {

    @Inject
    public ProfessionImpressListAdapter(@Nullable List<ProfessionImpress> data) {
        super(R.layout.item_recommend_school_list, data);
    }

    @Override
    protected void convert(@NonNull CrazyBaseViewHolder helper, ProfessionImpress item) {
        if (item != null) {
            GlideImageView logoImg = (GlideImageView) helper.getView(R.id.recommend_school_img);
            if (!TextUtils.isEmpty(item.getImg_url())) ;
            logoImg.load(ImageUtils.getImageUrl(item.getImg_url()));
            helper.setText(R.id.recommend_school_name_tv, "" + item.getKey_word());
        }
    }

}
