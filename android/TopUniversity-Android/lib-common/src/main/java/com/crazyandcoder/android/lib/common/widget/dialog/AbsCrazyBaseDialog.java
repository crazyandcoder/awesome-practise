package com.crazyandcoder.android.lib.common.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.crazyandcoder.android.lib.common.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class AbsCrazyBaseDialog extends Dialog {

    private Context context;
    private Unbinder mUnbinder;

    public AbsCrazyBaseDialog(@NonNull Context context) {
        super(context, R.style.commonDialog);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(context);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int layoutID = getLayoutID();
        if (layoutID == 0) {
            dismiss();
        } else {
            setContentView(inflater.inflate(layoutID, null));
        }
        mUnbinder = ButterKnife.bind(this);
        initView();
        initData();
        setCanceledOnTouchOutside(false);
    }

    @LayoutRes
    protected abstract int getLayoutID();

    protected abstract void initView();

    protected abstract void initData();


    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /***
     * 从底部弹出dialog
     */
    public AbsCrazyBaseDialog setBottomStyle() {
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(attributes);
        return this;
    }


    /***
     * 从底部弹出dialog
     */
    public AbsCrazyBaseDialog setCenterStyle() {
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.CENTER;
        getWindow().setAttributes(attributes);
        return this;
    }


    @Override
    public void show() {
        if (context instanceof Activity && !((Activity) context).isFinishing()) {
            super.show();
        }
    }

    @Override
    public void dismiss() {
        if (context instanceof Activity && !((Activity) context).isFinishing()) {
            super.dismiss();
        }
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }


}
