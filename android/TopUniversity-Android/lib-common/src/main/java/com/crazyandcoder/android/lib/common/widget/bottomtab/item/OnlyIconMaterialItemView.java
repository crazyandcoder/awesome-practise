package com.crazyandcoder.android.lib.common.widget.bottomtab.item;

/**
 * @ClassName: OnlyIconMaterialItemView
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 5:28 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 5:28 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.R;
import com.crazyandcoder.android.lib.common.widget.bottomtab.utils.Utils;
import com.crazyandcoder.android.lib.common.widget.bottomtab.view.RoundMessageView;


/**
 * 只有图标的材料设计项(用于垂直布局)
 */
public class OnlyIconMaterialItemView extends AbsBaseTabItem {

    private final RoundMessageView mMessages;
    private final ImageView mIcon;

    private Drawable mDefaultDrawable;
    private Drawable mCheckedDrawable;

    private int mDefaultColor;
    private int mCheckedColor;

    private String mTitle;

    private boolean mChecked;

    private boolean mTintIcon = true;

    public OnlyIconMaterialItemView(@NonNull Context context) {
        this(context, null);
    }

    public OnlyIconMaterialItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OnlyIconMaterialItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.common_item_material_only_icon, this, true);

        mIcon = findViewById(R.id.icon);
        mMessages = findViewById(R.id.messages);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return OnlyIconMaterialItemView.class.getName();
    }

    public void initialization(String title, Drawable drawable, Drawable checkedDrawable, boolean tintIcon, int color, int checkedColor) {

        mTitle = title;

        mDefaultColor = color;
        mCheckedColor = checkedColor;

        mTintIcon = tintIcon;

        if (mTintIcon) {
            mDefaultDrawable = Utils.tinting(drawable, mDefaultColor);
            mCheckedDrawable = Utils.tinting(checkedDrawable, mCheckedColor);
        } else {
            mDefaultDrawable = drawable;
            mCheckedDrawable = checkedDrawable;
        }

        mIcon.setImageDrawable(mDefaultDrawable);

        if (Build.VERSION.SDK_INT >= 21) {
            setBackground(new RippleDrawable(new ColorStateList(new int[][]{{}}, new int[]{0xFFFFFF & checkedColor | 0x56000000}), null, null));
        } else {
            setBackgroundResource(R.drawable.common_material_item_background);
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked == checked) {
            return;
        }

        mChecked = checked;

        // 切换颜色
        if (mChecked) {
            mIcon.setImageDrawable(mCheckedDrawable);
        } else {
            mIcon.setImageDrawable(mDefaultDrawable);
        }
    }

    @Override
    public void setMessageNumber(int number) {
        mMessages.setVisibility(View.VISIBLE);
        mMessages.setMessageNumber(number);
    }

    @Override
    public void setHasMessage(boolean hasMessage) {
        mMessages.setVisibility(View.VISIBLE);
        mMessages.setHasMessage(hasMessage);
    }

    @Override
    public void setTitle(String title) {
        // no title
    }

    @Override
    public void setDefaultDrawable(Drawable drawable) {
        if (mTintIcon) {
            mDefaultDrawable = Utils.tinting(drawable, mDefaultColor);
        } else {
            mDefaultDrawable = drawable;
        }

        if (!mChecked) {
            mIcon.setImageDrawable(mDefaultDrawable);
        }
    }

    @Override
    public void setSelectedDrawable(Drawable drawable) {
        if (mTintIcon) {
            mCheckedDrawable = Utils.tinting(drawable, mCheckedColor);
        } else {
            mCheckedDrawable = drawable;
        }

        if (mChecked) {
            mIcon.setImageDrawable(mCheckedDrawable);
        }
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    /**
     * 设置消息圆形的颜色
     */
    public void setMessageBackgroundColor(@ColorInt int color) {
        mMessages.tintMessageBackground(color);
    }

    /**
     * 设置消息数据的颜色
     */
    public void setMessageNumberColor(@ColorInt int color) {
        mMessages.setMessageNumberColor(color);
    }
}
