package com.crazyandcoder.android.lib.common.utils.screen;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.crazyandcoder.android.lib.common.R;

/**
 * @ClassName: CrazyScreenInfoUtils
 * @Description: 获取屏幕相关的参数
 * <p>
 * 屏幕区域 = 状态栏 + 标题栏 +view区域
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/29 9:53 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/29 9:53 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyScreenInfoUtils {

    /**
     * 获取屏幕区域的宽高尺寸，返回一个DisplayMetrics
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Activity context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        return displayMetrics;
    }

    /**
     * 获取应用程序区域宽高尺寸，返回一个Rect
     *
     * @param activity
     * @return
     */
    public static Rect getRect(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        //状态栏高度
        int statusBarHeight = rect.top;
        return rect;
    }

    /**
     * 获取屏幕view的参数，返回一个display
     *
     * @param context
     * @return
     */
    public static Display getDisplay(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager.getDefaultDisplay();
    }

    /**
     * 获取导航栏高度
     *
     * @return
     */
    public static int getToolBarHeight(Context context) {
        Resources resources = context.getResources();
        float dimension = resources.getDimension(R.dimen.base_ax_Toolbar_height);
        return (int) dimension;
    }

}
