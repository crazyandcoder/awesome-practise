package com.crazyandcoder.android.lib.common.utils.log;

import android.util.Log;

/**
 * @ClassName: BaseLog
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 11:05 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 11:05 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseLog {

    private static final int MAX_LENGTH = 4000;

    public static void printDefault(int type, String tag, String msg) {

        int index = 0;
        int length = msg.length();
        int countOfSub = length / MAX_LENGTH;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + MAX_LENGTH);
                printSub(type, tag, sub);
                index += MAX_LENGTH;
            }
            printSub(type, tag, msg.substring(index, length));
        } else {
            printSub(type, tag, msg);
        }
    }

    private static void printSub(int type, String tag, String sub) {
        switch (type) {
            case CrazyLog.V:
                Log.v(tag, sub);
                break;
            case CrazyLog.D:
                Log.d(tag, sub);
                break;
            case CrazyLog.I:
                Log.i(tag, sub);
                break;
            case CrazyLog.W:
                Log.w(tag, sub);
                break;
            case CrazyLog.E:
                Log.e(tag, sub);
                break;
            case CrazyLog.A:
                Log.wtf(tag, sub);
                break;
        }
    }

}