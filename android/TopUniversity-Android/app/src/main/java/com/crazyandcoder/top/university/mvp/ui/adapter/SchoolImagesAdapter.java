package com.crazyandcoder.top.university.mvp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.common.widget.banner.adapter.BannerAdapter;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.api.ImageUrl;
import com.crazyandcoder.top.university.bean.resp.SchoolImage;
import com.sunfusheng.GlideImageView;

import java.util.List;

public class SchoolImagesAdapter extends BannerAdapter<SchoolImage, SchoolImagesAdapter.SchoolBannerViewHolder> {


    public SchoolImagesAdapter(List<SchoolImage> datas) {
        super(datas);
    }

    @Override
    public SchoolBannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new SchoolBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_title_num, parent, false));
    }

    @Override
    public void onBindView(SchoolBannerViewHolder holder, SchoolImage data, int position, int size) {
        holder.title.setText(data.getTitle());
        holder.imageView.load(getUrl(data.getM_url()));
        //可以在布局文件中自己实现指示器，亦可以使用banner提供的方法自定义指示器，目前样式较少，后面补充
        holder.numIndicator.setText((position + 1) + "/" + size);
    }

    class SchoolBannerViewHolder extends RecyclerView.ViewHolder {
        GlideImageView imageView;
        TextView title;
        TextView numIndicator;

        public SchoolBannerViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            title = view.findViewById(R.id.bannerTitle);
            numIndicator = view.findViewById(R.id.numIndicator);
        }
    }

    private String getUrl(String m_url) {
        return ImageUrl.image_url + m_url;
    }
}
