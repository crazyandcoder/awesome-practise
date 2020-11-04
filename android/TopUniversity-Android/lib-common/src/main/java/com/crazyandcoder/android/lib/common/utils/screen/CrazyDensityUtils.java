package com.crazyandcoder.android.lib.common.utils.screen;

import android.content.Context;
import android.util.TypedValue;

/**
 * @ClassName: CrazyDensityUtils
 * @Description: 尺寸单位转换
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:32 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:32 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyDensityUtils {

    /**
     * dp转px
     *
     * @param dpVal
     * @param
     * @return
     */

    public static int dp2px(Context context, float dpVal) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());

    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */

    public static int sp2px(Context context, float spVal) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,

                spVal, context.getResources().getDisplayMetrics());

    }


    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */

    public static float px2dp(Context context, float pxVal) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */

    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }



}
