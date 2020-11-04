package com.crazyandcoder.top.university.mvp.ui.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.widget.recycler.AbsCrazyBaseAdapter;
import com.crazyandcoder.android.lib.common.widget.recycler.CrazyBaseViewHolder;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;

import java.util.List;

import javax.inject.Inject;

public class ProfessionListAdapter extends AbsCrazyBaseAdapter<ProfessionHotListResp, ProfessionListAdapter.RecommendProfessionViewHolder> {

    @Inject
    public ProfessionListAdapter(@Nullable List<ProfessionHotListResp> data) {
        super(R.layout.item_profession_list, data);
    }

    @Override
    protected void convert(@NonNull RecommendProfessionViewHolder holder, ProfessionHotListResp data) {
        if (data != null) {
            holder.itemName.setText("" + data.getName());
            holder.level1.setText("" + data.getLevel2());
            holder.level3.setText("" + data.getLevel3Name());
            holder.degree.setText("" + data.getDegree());
            holder.limit.setText("" + data.getLimitYear());
            holder.bannerItemL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(holder.getLayoutPosition(), data);
                    }
                }
            });
        }
    }


    class RecommendProfessionViewHolder extends CrazyBaseViewHolder {

        private TextView itemName;
        private TextView level1;
        private TextView level3;
        private TextView degree;
        private TextView limit;
        private LinearLayout bannerItemL;

        public RecommendProfessionViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_recommend_profession_name_tv);
            level1 = itemView.findViewById(R.id.item_recommend_profession_level1_tv);
            level3 = itemView.findViewById(R.id.item_recommend_profession_level3_tv);
            degree = itemView.findViewById(R.id.item_recommend_profession_degree_tv);
            limit = itemView.findViewById(R.id.item_recommend_profession_limit_tv);
            bannerItemL = itemView.findViewById(R.id.profession_banner_item_l);
        }


    }

    public interface OnProfessionItemClickListener {
        void onClick(int position, ProfessionHotListResp resp);
    }

    private OnProfessionItemClickListener listener;

    public void setOnProfessionItemClickListener(OnProfessionItemClickListener listener) {
        this.listener = listener;
    }


}
