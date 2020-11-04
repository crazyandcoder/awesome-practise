package com.crazyandcoder.top.university.mvp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.common.widget.banner.adapter.BannerAdapter;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.resp.ProfessionHotListResp;

import java.util.List;

import javax.inject.Inject;

public class RecommendProfessionHotListAdapter extends BannerAdapter<ProfessionHotListResp, RecommendProfessionHotListAdapter.RecommendProfessionViewHolder> {

    @Inject
    public RecommendProfessionHotListAdapter(@Nullable List<ProfessionHotListResp> data) {
        super(data);
    }

    @Override
    public RecommendProfessionViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new RecommendProfessionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_profession_list, parent, false));
    }

    @Override
    public void onBindView(RecommendProfessionViewHolder holder, ProfessionHotListResp data, int position, int size) {
        holder.name.setText("" + data.getName());
        holder.itemName.setText("" + data.getName());
        holder.level1.setText("" + data.getLevel2());
        holder.level2.setText("" + data.getLevel2Name());
        holder.level3.setText("" + data.getLevel3Name());
        holder.degree.setText("" + data.getDegree());
        holder.limit.setText("" + data.getLimitYear());
        holder.bannerItemL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(position, data);
                }
            }
        });
    }

    class RecommendProfessionViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView itemName;
        private TextView level1;
        private TextView level2;
        private TextView level3;
        private TextView degree;
        private TextView limit;
        private LinearLayout bannerItemL;

        public RecommendProfessionViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recommend_profession_name_tv);
            itemName = itemView.findViewById(R.id.item_recommend_profession_name_tv);
            level1 = itemView.findViewById(R.id.item_recommend_profession_level1_tv);
            level2 = itemView.findViewById(R.id.item_recommend_profession_level2_tv);
            level3 = itemView.findViewById(R.id.item_recommend_profession_level3_tv);
            degree = itemView.findViewById(R.id.item_recommend_profession_degree_tv);
            limit = itemView.findViewById(R.id.item_recommend_profession_limit_tv);
            bannerItemL = itemView.findViewById(R.id.profession_banner_item_l);
        }


    }

    public interface OnItemClickListener {
        void onClick(int position, ProfessionHotListResp resp);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
