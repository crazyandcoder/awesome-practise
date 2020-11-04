package com.crazyandcoder.android.lib.common.widget.cardview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.R;

/**
 * @ClassName: CrazyCardView
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 6:30 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 6:30 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyCardView extends FrameLayout {
    public CrazyCardView(@NonNull Context context) {
        this(context, null);
    }

    public CrazyCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CrazyCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.common_CardView);
        float aFloat = attributes.getFloat(R.styleable.common_CardView_common_bg_alpha, 0.02f);
        int aInt = attributes.getInt(R.styleable.common_CardView_common_bg_corners, 8);
        attributes.recycle();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(16f);
        }
        setBackground(new RoundDrawable(aFloat, aInt));
    }
}