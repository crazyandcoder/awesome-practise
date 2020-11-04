package com.crazyandcoder.android.lib.base.widget.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * @author liji
 */
public class ToastUtils {

    /**
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {
        new CenterToast(context, msg, Toast.LENGTH_SHORT).show();
    }

}
