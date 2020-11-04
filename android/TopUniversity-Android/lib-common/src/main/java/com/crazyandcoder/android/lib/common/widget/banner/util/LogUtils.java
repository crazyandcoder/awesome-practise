package com.crazyandcoder.android.lib.common.widget.banner.util;

import android.util.Log;


public class LogUtils {
    public static final String TAG = "banner_log";


    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void e(String msg) {
        Log.e(TAG, msg);
    }

    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void v(String msg) {
        Log.v(TAG, msg);
    }

    public static void w(String msg) {
        Log.w(TAG, msg);
    }
}
