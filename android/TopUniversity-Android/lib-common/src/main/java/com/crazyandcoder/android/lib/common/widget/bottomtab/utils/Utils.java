package com.crazyandcoder.android.lib.common.widget.bottomtab.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * @ClassName: Utils
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 9:59 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 9:59 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Utils {
    /**
     * Drawable 染色
     *
     * @param drawable 染色对象
     * @param color    颜色
     * @return 染色后的资源
     */
    public static Drawable tinting(Drawable drawable, int color) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        wrappedDrawable.mutate();
        DrawableCompat.setTint(wrappedDrawable, color);
        return wrappedDrawable;
    }

    public static Drawable newDrawable(Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        return constantState != null ? constantState.newDrawable() : drawable;
    }

    /**
     * 获取colorPrimary的颜色,需要V7包的支持
     *
     * @param context 上下文
     * @return 0xAARRGGBB
     */
    public static int getColorPrimary(Context context) {
        Resources res = context.getResources();
        int attrRes = res.getIdentifier("colorPrimary", "attr", context.getPackageName());
        if (attrRes == 0) {
            return 0xFF009688;
        }
        return ContextCompat.getColor(context, getResourceId(context, attrRes));
    }

    /**
     * 获取自定义属性的资源ID
     *
     * @param context 上下文
     * @param attrRes 自定义属性
     * @return resourceId
     */
    private static int getResourceId(Context context, int attrRes) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return typedValue.resourceId;
    }

}
