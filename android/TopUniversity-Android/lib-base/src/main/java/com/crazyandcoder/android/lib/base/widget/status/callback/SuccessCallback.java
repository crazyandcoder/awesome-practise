package com.crazyandcoder.android.lib.base.widget.status.callback;

import android.content.Context;
import android.view.View;

/**
 * @ClassName: SuccessCallback
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:10 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:10 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SuccessCallback extends Callback {
    public SuccessCallback(View view, Context context, OnReloadListener onReloadListener) {
        super(view, context, onReloadListener);
    }

    @Override
    protected int onCreateView() {
        return 0;
    }

    /**
     * @deprecated Use {@link #showWithCallback(boolean successVisible)} instead.
     */
    public void hide() {
        obtainRootView().setVisibility(View.INVISIBLE);
    }

    public void show() {
        obtainRootView().setVisibility(View.VISIBLE);
    }

    public void showWithCallback(boolean successVisible) {
        obtainRootView().setVisibility(successVisible ? View.VISIBLE : View.INVISIBLE);
    }

}

