package com.crazyandcoder.android.lib.common.widget.cardview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ClassName: RoundDrawable
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 8:31 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 8:31 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RoundDrawable extends Drawable {

    private Float mRadius;
    private int mAlpha;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private RectF mRectF = new RectF();

    public RoundDrawable() {

    }

    public RoundDrawable(Float mRadius, int mAlpha) {
        this.mRadius = mRadius;
        this.mAlpha = mAlpha;
        mPaint.setColor(Color.WHITE);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }


    @Override
    public void setBounds(Rect bounds) {
        super.setBounds(bounds);
        mRectF.set(bounds);
    }


    @Override
    public void getOutline(Outline outline) {
        super.getOutline(outline);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            outline.setRoundRect(getBounds(), mRadius);
            outline.setAlpha(mAlpha);
        }
    }
}
