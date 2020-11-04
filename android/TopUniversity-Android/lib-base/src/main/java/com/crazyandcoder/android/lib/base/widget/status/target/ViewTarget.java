package com.crazyandcoder.android.lib.base.widget.status.target;

import android.view.View;
import android.view.ViewGroup;

import com.crazyandcoder.android.lib.base.widget.status.callback.Callback;
import com.crazyandcoder.android.lib.base.widget.status.callback.SuccessCallback;
import com.crazyandcoder.android.lib.base.widget.status.core.LoadLayout;

/**
 * @ClassName: ViewTarget
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:14 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:14 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewTarget implements ITarget {

    @Override
    public boolean equals(Object target) {
        return target instanceof View;
    }

    @Override
    public LoadLayout replaceView(Object target, Callback.OnReloadListener onReloadListener) {
        View oldContent = (android.view.View) target;
        ViewGroup contentParent = (ViewGroup) (oldContent.getParent());
        int childIndex = 0;
        int childCount = contentParent == null ? 0 : contentParent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (contentParent.getChildAt(i) == oldContent) {
                childIndex = i;
                break;
            }
        }
        if (contentParent != null) {
            contentParent.removeView(oldContent);
        }
        ViewGroup.LayoutParams oldLayoutParams = oldContent.getLayoutParams();
        LoadLayout loadLayout = new LoadLayout(oldContent.getContext(), onReloadListener);
        loadLayout.setupSuccessLayout(new SuccessCallback(oldContent, oldContent.getContext(),onReloadListener));
        if (contentParent != null) {
            contentParent.addView(loadLayout, childIndex, oldLayoutParams);
        }
        return loadLayout;
    }
}
