package com.crazyandcoder.android.lib.base.widget.status.target;

import com.crazyandcoder.android.lib.base.widget.status.callback.Callback;
import com.crazyandcoder.android.lib.base.widget.status.core.LoadLayout;

/**
 * @ClassName: ITarget
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:12 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:12 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ITarget {
    /**
     *
     * @param target
     * @return
     * v1.3.8
     */
    boolean equals(Object target);
    /**
     * 1.removeView 2.确定LP 3.addView
     * @param target
     * @param onReloadListener
     * @return
     * v1.3.8
     */
    LoadLayout replaceView(Object target, Callback.OnReloadListener onReloadListener);
}
