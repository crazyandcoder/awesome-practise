package com.crazyandcoder.android.lib.base.utils;

import android.graphics.Color;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {
    private static final String TAG = ObjectUtils.class.getSimpleName();
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isEmpty(Object obj) {
        return null == obj;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static boolean isEmpty(Object[] objs) {
        return null == objs || objs.length <= 0;
    }

    public static boolean isEmpty(int[] objs) {
        return null == objs || objs.length <= 0;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return null == charSequence || charSequence.length() <= 0;
    }

    public static boolean isNotEmpty(Collection collection) {
        return null != collection && !collection.isEmpty();
    }

    public static boolean isNotEmpty(Object obj) {
        return null != obj;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Map map) {
        return null != map && !map.isEmpty();
    }

    public static boolean isNotEmpty(Object[] objs) {
        return null != objs && objs.length > 0;
    }

    public static boolean isNotEmpty(int[] objs) {
        return null != objs && objs.length > 0;
    }

    public static boolean isNotEmpty(CharSequence charSequence) {
        return null != charSequence && charSequence.length() > 0;
    }

    public static int getColor(String colorStr, int defaultColor) {
        if (isEmpty(colorStr)) {
            return defaultColor;
        }
        try {
            return Color.parseColor(colorStr);
        } catch (Exception e) {
            return defaultColor;
        }
    }
}
