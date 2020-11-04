package com.crazyandcoder.top.university.mvp.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.common.widget.recycler.AbsCrazyBaseAdapter;
import com.crazyandcoder.android.lib.common.widget.recycler.CrazyBaseViewHolder;
import com.crazyandcoder.top.university.R;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfo;
import com.crazyandcoder.top.university.bean.resp.SchoolMoreInfoResp;
import com.zzhoujay.richtext.RichText;

import java.util.List;

public class SchoolMoreInfoAdapter extends AbsCrazyBaseAdapter<SchoolMoreInfo, SchoolMoreInfoAdapter.SchoolMoreInfoAdapterViewHolder> {


    public SchoolMoreInfoAdapter(@Nullable List<SchoolMoreInfo> data) {
        super(R.layout.item_school_more_info, data);
    }

    @Override
    protected void convert(@NonNull SchoolMoreInfoAdapterViewHolder helper, SchoolMoreInfo item) {
        if (item.getTypeKey().equals("aboutXueXiaoGaiKuang")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_1);
            helper.key.setText("学校概况");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutYuanXiSheZhi")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_2);
            helper.key.setText("学院设置");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutShiZiLiLiang")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_3);
            helper.key.setText("师资力量");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutTiJianBiaoZhun")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_4);
            helper.key.setText("师资力量");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutShouFeiBiaoZhun")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_5);
            helper.key.setText("收费标准");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutJiuYeQingKuang")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_6);
            helper.key.setText("就业情况");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutLuQuGuiZe")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_7);
            helper.key.setText("录取规则");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutXueXiaoLingDao")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_8);
            helper.key.setText("学校领导");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutZhongDianXueKe")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_9);
            helper.key.setText("重点学科");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutZhaoShengZhengCe")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_10);
            helper.key.setText("招生政策");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }else if (item.getTypeKey().equals("aboutYiLiuXueKe")) {
            helper.logo.setImageResource(R.drawable.ic_school_more_11);
            helper.key.setText("一流学科");
            if (TextUtils.isEmpty(item.getTypeValue())) {
                helper.value.setText("      暂无");
            } else {
                RichText.from(item.getTypeValue()).into(helper.value);
            }
        }
    }

    class SchoolMoreInfoAdapterViewHolder extends CrazyBaseViewHolder {

        private ImageView logo;
        private TextView key;
        private TextView value;

        public SchoolMoreInfoAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = (ImageView) itemView.findViewById(R.id.shcool_more_key_img);
            key = (TextView) itemView.findViewById(R.id.shcool_more_key_tv);
            value = (TextView) itemView.findViewById(R.id.school_more_value_tv);
        }
    }
}
