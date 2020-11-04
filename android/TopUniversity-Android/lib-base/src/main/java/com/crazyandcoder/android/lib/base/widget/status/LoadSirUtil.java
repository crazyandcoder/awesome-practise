package com.crazyandcoder.android.lib.base.widget.status;

import android.os.Looper;

import com.crazyandcoder.android.lib.base.widget.status.target.ITarget;

import java.util.List;

/**
 * @ClassName: LoadSirUtil
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:12 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:12 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoadSirUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static ITarget getTargetContext(Object target, List<ITarget> targetContextList) {
        for (ITarget targetContext : targetContextList) {
            if (targetContext.equals(target)) {
                return targetContext;
            }

        }
        throw new IllegalArgumentException("No TargetContext fit it");
    }
}

