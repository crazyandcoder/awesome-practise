package com.crazyandcoder.android.lib.common.utils.log;

import android.text.TextUtils;
import android.util.Log;

/**
 * @ClassName: CrazyLogUtil
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 11:05 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 11:05 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyLogUtil {

    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

}