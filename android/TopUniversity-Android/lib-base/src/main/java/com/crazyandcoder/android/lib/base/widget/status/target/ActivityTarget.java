package com.crazyandcoder.android.lib.base.widget.status.target;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.crazyandcoder.android.lib.base.widget.status.callback.Callback;
import com.crazyandcoder.android.lib.base.widget.status.callback.SuccessCallback;
import com.crazyandcoder.android.lib.base.widget.status.core.LoadLayout;

/**
 * @ClassName: ActivityTarget
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:14 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:14 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ActivityTarget implements ITarget {

    @Override
    public boolean equals(Object target) {
        return target instanceof Activity;
    }

    @Override
    public LoadLayout replaceView(Object target, Callback.OnReloadListener onReloadListener) {
        Activity activity = (Activity) target;
        ViewGroup contentParent = activity.findViewById(android.R.id.content);
        int childIndex = 0;
        View oldContent = contentParent.getChildAt(childIndex);
        contentParent.removeView(oldContent);

        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        LoadLayout loadLayout = new LoadLayout(activity, onReloadListener);
        loadLayout.setupSuccessLayout(new SuccessCallback(oldContent, activity,
                onReloadListener));
        contentParent.addView(loadLayout, childIndex, oldLayoutParams);
        return loadLayout;
    }
}

