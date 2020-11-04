package com.crazyandcoder.android.lib.base.widget.toolbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.crazyandcoder.android.lib.base.R;

/**
 * @ClassName: CustomToolbar
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/17 5:31 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/17 5:31 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomToolbar extends RelativeLayout {

    private RadioGroup centerRadioGroup;
    private RadioButton leftRadioButton;
    private RadioButton rightRadioButton;
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView leftTitleTv;
    private TextView leftSubTitleTv;
    private TextView centerTitleTv;
    private TextView rightTitleTv;
    private View baseLineView;
    private RelativeLayout bgRl;
    private Context context;


    public CustomToolbar(Context context) {
        this(context, null);
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.base_custome_toolbar, this);
        bgRl = (RelativeLayout) view.findViewById(R.id.custom_toolbar_bg_rl);
        leftImg = (ImageView) view.findViewById(R.id.custom_toolbar_left_img);
        rightImg = (ImageView) view.findViewById(R.id.custom_toolbar_right_img);
        leftTitleTv = (TextView) view.findViewById(R.id.custom_toolbar_left_title_tv);
        leftSubTitleTv = (TextView) view.findViewById(R.id.custom_toolbar_left_subTitle_tv);
        centerTitleTv = (TextView) view.findViewById(R.id.custom_toolbar_center_title_tv);
        rightTitleTv = (TextView) view.findViewById(R.id.custom_toolbar_right_title_tv);
        baseLineView = (View) view.findViewById(R.id.custom_toolbar_baseline_view);
        centerRadioGroup = (RadioGroup) view.findViewById(R.id.custom_toolbar_center_radiogroup);
        leftRadioButton = (RadioButton) view.findViewById(R.id.custom_toolbar_center_left_radiobutton);
        rightRadioButton = (RadioButton) view.findViewById(R.id.custom_toolbar_center_right_radiobutton);
        init();
    }

    public void init() {
        leftImg.setVisibility(GONE);
        rightImg.setVisibility(GONE);
        leftTitleTv.setVisibility(GONE);
        leftSubTitleTv.setVisibility(GONE);
        centerTitleTv.setVisibility(GONE);
        rightTitleTv.setVisibility(GONE);
        baseLineView.setVisibility(GONE);
        centerRadioGroup.setVisibility(GONE);
    }

    public void back(OnClickListener listener) {
        leftImg.setVisibility(VISIBLE);
        leftImg.setOnClickListener(listener);
    }

    public void setBackground(int color) {
        if (bgRl != null) {
            bgRl.setBackgroundColor(color);
        }
    }

    public RadioGroup getCenterRadioGroup() {
        centerRadioGroup.setVisibility(VISIBLE);
        return centerRadioGroup;
    }

    /**
     * 设置左边按钮背景
     *
     * @param resId
     */
    public void setLeftImage(int resId) {
        setLeftImage(resId, null);

    }

    /**
     * 设置左边按钮背景，并设置点击事件
     *
     * @param resId
     * @param clickListener
     */
    public void setLeftImage(int resId, OnClickListener clickListener) {
        if (leftImg != null) {
            leftImg.setVisibility(VISIBLE);
            if (clickListener != null) {
                leftImg.setOnClickListener(clickListener);
            }
            if (resId != -1) {
                leftImg.setImageResource(resId);
            }
        }

    }

    /**
     * 设置右边按钮背景
     *
     * @param resId
     */
    public void setRightImage(int resId) {
        setRightImage(resId, null);

    }

    /**
     * 设置右边按钮背景，并设置点击事件
     *
     * @param resId
     * @param clickListener
     */
    public void setRightImage(int resId, OnClickListener clickListener) {
        if (rightImg != null) {
            rightImg.setVisibility(VISIBLE);
            if (clickListener != null) {
                rightImg.setOnClickListener(clickListener);
            }
            if (resId != -1) {
                rightImg.setImageResource(resId);
            }
        }

    }


    /**
     * 设置左边文字
     *
     * @param title
     */
    public void setLeftTitle(String title) {
        setLeftTitle(title, null, null, null);
    }


    /**
     * 设置左边文字
     *
     * @param title
     */
    public void setLeftTitle(String title, Typeface tf) {

        setLeftTitle(title, null, null, tf);
    }


    /**
     * 设置左边文字
     *
     * @param title
     */
    public void setLeftTitle(String title, OnClickListener listener) {
        setLeftTitle(title, null, listener, null);
    }


    /**
     * 设置左边文字
     *
     * @param title
     */
    public void setLeftTitle(String title, String colorRes) {
        setLeftTitle(title, colorRes, null, null);
    }


    /**
     * 设置左边文字并设置点击事件
     *
     * @param title
     * @param clickListener
     */
    public void setLeftTitle(String title, String colorRes, OnClickListener clickListener, Typeface tf) {
        if (!TextUtils.isEmpty(title)) {
            if (leftTitleTv != null) {
                leftTitleTv.setVisibility(VISIBLE);
                leftTitleTv.setText(title);
                if (clickListener != null) {
                    leftTitleTv.setOnClickListener(clickListener);
                }
                if (!TextUtils.isEmpty(colorRes)) {
                    leftTitleTv.setTextColor(Color.parseColor(colorRes));
                }

                if (tf != null) {
                    leftTitleTv.setTypeface(tf);

                }
            }

        }
    }

    /**
     * 设置左边文字
     *
     * @param title
     */
    public void setLeftSubTitle(String title) {
        setLeftSubTitle(title, null, null);
    }


    /**
     * 设置左边文字
     *
     * @param title
     */
    public void setLeftSubTitle(String title, OnClickListener listener) {
        setLeftSubTitle(title, null, listener);
    }


    /**
     * 设置左边文字
     *
     * @param title
     */
    public void setLeftSubTitle(String title, String colorRes) {
        setLeftSubTitle(title, colorRes, null);
    }


    /**
     * 设置左边文字并设置点击事件
     *
     * @param title
     * @param clickListener
     */
    public void setLeftSubTitle(String title, String colorRes, OnClickListener clickListener) {
        if (!TextUtils.isEmpty(title)) {
            if (leftSubTitleTv != null) {
                leftSubTitleTv.setVisibility(VISIBLE);
                leftSubTitleTv.setText(title);
                if (clickListener != null) {
                    leftSubTitleTv.setOnClickListener(clickListener);
                }
                if (!TextUtils.isEmpty(colorRes)) {
                    leftSubTitleTv.setTextColor(Color.parseColor(colorRes));
                }
            }

        }
    }

    /**
     * 设置中间文字
     *
     * @param title
     */
    public void setCenterTitle(String title) {
        setCenterTitle(title, null, null);
    }

    /**
     * 设置中间文字
     *
     * @param title
     */
    public void setCenterTitle(String title, OnClickListener listener) {
        setCenterTitle(title, null, listener);
    }

    /**
     * 设置中间文字
     *
     * @param title
     */
    public void setCenterTitle(String title, String color) {
        setCenterTitle(title, color, null);
    }

    /**
     * 设置中间文字并设置点击事件
     *
     * @param title
     * @param clickListener
     */
    public void setCenterTitle(String title, String colorRes, OnClickListener clickListener) {
        if (!TextUtils.isEmpty(title)) {
            if (centerTitleTv != null) {
                centerTitleTv.setVisibility(VISIBLE);
                centerTitleTv.setText(title);
                ;
                if (clickListener != null) {
                    centerTitleTv.setOnClickListener(clickListener);
                }
                if (!TextUtils.isEmpty(colorRes)) {
                    centerTitleTv.setTextColor(Color.parseColor(colorRes));
                }
            }

        }
    }

    /**
     * 设置右边文字
     *
     * @param title
     */
    public void setRightTitle(String title) {
        setRightTitle(title, null, null);
    }

    /**
     * 设置右边文字
     *
     * @param title
     */
    public void setRightTitle(String title, OnClickListener listener) {
        setRightTitle(title, null, listener);
    }

    /**
     * 设置右边文字
     *
     * @param title
     */
    public void setRightTitle(String title, String colorRes) {
        setRightTitle(title, colorRes, null);
    }


    /**
     * 设置右边文字并设置点击事件
     *
     * @param title
     * @param clickListener
     */
    public void setRightTitle(String title, String colorRes, OnClickListener clickListener) {
        if (!TextUtils.isEmpty(title)) {
            if (rightTitleTv != null) {
                rightTitleTv.setVisibility(VISIBLE);
                rightTitleTv.setText(title);
                ;
                if (clickListener != null) {
                    rightTitleTv.setOnClickListener(clickListener);
                }
                if (!TextUtils.isEmpty(colorRes)) {
                    rightTitleTv.setTextColor(Color.parseColor(colorRes));
                }
            }

        }
    }

    public void showBaseLine() {
        if (baseLineView != null) {
            baseLineView.setVisibility(VISIBLE);
        }
    }
}
