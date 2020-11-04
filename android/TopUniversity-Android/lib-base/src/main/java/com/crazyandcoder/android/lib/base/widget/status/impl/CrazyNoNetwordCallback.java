package com.crazyandcoder.android.lib.base.widget.status.impl;

import android.content.Context;
import android.view.View;

import com.crazyandcoder.android.lib.base.R;
import com.crazyandcoder.android.lib.base.widget.status.callback.Callback;

/**
 * @ClassName: CrazyNoNetwordCallback
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:33 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:33 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyNoNetwordCallback extends Callback {
    private OnReloadListener reloadListener;


    @Override
    public Callback setCallback(View view, Context context, OnReloadListener onReloadListener) {
        reloadListener = onReloadListener;
        return super.setCallback(view, context, onReloadListener);
    }

    @Override
    protected int onCreateView() {
        return R.layout.base_callback_crazy_no_network;
    }

    @Override
    protected void onViewCreate(Context context, View view) {
        super.onViewCreate(context, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reloadListener != null) {
                    reloadListener.onReload(v);
                }
            }
        });

        if (view.findViewById(R.id.no_net_retry_tv) != null) {
            view.findViewById(R.id.no_net_retry_tv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (reloadListener != null) {
                        reloadListener.onReload(v);
                    }
                }
            });
        }
    }
}