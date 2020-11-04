package com.crazyandcoder.android.lib.base.widget.status.callback;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StyleRes;

/**
 * @ClassName: HintCallback
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:09 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:09 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HintCallback extends Callback {

    private String title;
    private String subTitle;
    private int imgResId;
    private int titleStyleRes;
    private int subTitleStyleRes;

    public HintCallback(Builder builder) {
        this.title = builder.title;
        this.subTitle = builder.subTitle;
        this.imgResId = builder.imgResId;
        this.subTitleStyleRes = builder.subTitleStyleRes;
        this.titleStyleRes = builder.titleStyleRes;
    }

    @Override
    protected int onCreateView() {
        return 0;
    }

    @Override
    protected View onBuildView(Context context) {
        return new LinearLayout(context);
    }

    @Override
    protected void onViewCreate(Context context, View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        LinearLayout ll = (LinearLayout) view;
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.CENTER);
        if (imgResId != -1) {
            ImageView ivImage = new ImageView(context);
            ivImage.setBackgroundResource(imgResId);
            ll.addView(ivImage, lp);
        }
        if (!TextUtils.isEmpty(title)) {
            TextView tvTitle = new TextView(context);
            tvTitle.setText(title);
            if (titleStyleRes == -1) {
                tvTitle.setTextAppearance(context, android.R.style.TextAppearance_Large);
            } else {
                tvTitle.setTextAppearance(context, titleStyleRes);
            }
            ll.addView(tvTitle, lp);
        }
        if (!TextUtils.isEmpty(subTitle)) {
            TextView tvSubtitle = new TextView(context);
            tvSubtitle.setText(subTitle);
            if (subTitleStyleRes == -1) {
                tvSubtitle.setTextAppearance(context, android.R.style.TextAppearance_Small);
            } else {
                tvSubtitle.setTextAppearance(context, subTitleStyleRes);
            }
            ll.addView(tvSubtitle, lp);
        }
    }

    public static class Builder {
        private String title;
        private String subTitle;
        private int imgResId = -1;
        private int subTitleStyleRes = -1;
        private int titleStyleRes = -1;

        public Builder setHintImg(@DrawableRes int imgResId) {
            this.imgResId = imgResId;
            return this;
        }

        public Builder setTitle(String title) {
            return setTitle(title, -1);
        }

        public Builder setTitle(String title, @StyleRes int titleStyleRes) {
            this.title = title;
            this.titleStyleRes = titleStyleRes;
            return this;
        }

        public Builder setSubTitle(String subTitle) {
            return setSubTitle(subTitle, -1);
        }

        public Builder setSubTitle(String subTitle, @StyleRes int subTitleStyleRes) {
            this.subTitle = subTitle;
            this.subTitleStyleRes = subTitleStyleRes;
            return this;
        }

        public HintCallback build() {
            return new HintCallback(this);
        }
    }
}
