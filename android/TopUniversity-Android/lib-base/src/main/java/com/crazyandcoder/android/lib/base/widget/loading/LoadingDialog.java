package com.crazyandcoder.android.lib.base.widget.loading;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazyandcoder.android.lib.base.R;


public class LoadingDialog {
    Context context;
    AlertDialog alert;
    private ImageView iv_loading;
    private AnimationDrawable anim;

    public LoadingDialog(Context context) {
        this.context = context;
    }

    public void showLoadingDialog( boolean isCancel) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.base_currency_dialog);
            alert = builder.create();
            alert.setCancelable(isCancel);
            if (alert != null) {
                if (!((Activity) context).isFinishing() && !alert.isShowing()) {
                    alert.show();
                }
            }
            Window window = alert.getWindow();
            window.setLayout(dip2px(150), dip2px(150));

            window.setContentView(R.layout.base_loading_dialog);
            iv_loading = (ImageView) window.findViewById(R.id.iv_loading);
            anim = (AnimationDrawable) iv_loading.getDrawable();
            if (anim != null)
                anim.start();
            dis_Dialog();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showLoadingDialogBottomTip(String dialogText, boolean isCancel) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.base_currency_dialog);
            alert = builder.create();
            alert.setCancelable(isCancel);
            if (alert != null) {
                if (!((Activity) context).isFinishing() && !alert.isShowing()) {
                    alert.show();
                }
            }
            Window window = alert.getWindow();
            window.setLayout(dip2px(150), dip2px(150));
            window.setContentView(R.layout.base_loading_dialog);
            iv_loading = (ImageView) window.findViewById(R.id.iv_loading);
            TextView tv_loading_bottom_text = (TextView) window.findViewById(R.id.tv_loading_bottom_text);
            tv_loading_bottom_text.setText(dialogText);
            tv_loading_bottom_text.setVisibility(View.VISIBLE);
            anim = (AnimationDrawable) iv_loading.getDrawable();
            if (anim != null)
                anim.start();
            dis_Dialog();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeLoadingDialog() {
        try {
            if (alert != null && iv_loading != null && alert.isShowing() && anim != null) {
                alert.cancel();
                anim.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dis_Dialog() {
        CountDownTimer Timers = new CountDownTimer(30000L, 1000L) {
            @Override
            public void onTick(long arg0) {

            }

            @Override
            public void onFinish() {
                if (alert.isShowing())
                    removeLoadingDialog();
            }
        };
        Timers.start();
    }

    public boolean isShowLoading() {
        if (alert != null) {
            if (alert.isShowing()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private int dip2px(float value) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }
}
