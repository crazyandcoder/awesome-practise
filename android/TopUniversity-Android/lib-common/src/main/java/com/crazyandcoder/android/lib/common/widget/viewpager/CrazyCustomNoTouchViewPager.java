package com.crazyandcoder.android.lib.common.widget.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @ClassName: CustomNoTouchViewPager
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 7:51 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 7:51 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyCustomNoTouchViewPager extends ViewPager {

    public CrazyCustomNoTouchViewPager(@NonNull Context context) {
        super(context);
    }

    public CrazyCustomNoTouchViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
