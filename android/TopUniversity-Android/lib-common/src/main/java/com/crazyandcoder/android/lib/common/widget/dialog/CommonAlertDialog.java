package com.crazyandcoder.android.lib.common.widget.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.crazyandcoder.android.lib.common.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: CommonAlertDialog
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/20 2:49 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/20 2:49 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CommonAlertDialog extends AbsCrazyBaseDialog {


    TextView mCommonDialogCancelTv;
    TextView mCommonDialogConfirmTv;
    TextView mCommonDialogTitleTv;
    TextView mCommonDialogContentTv;

    private String title;
    private String content;
    private String leftText;
    private String rightText;
    private Context context;
    private ICrazyBaseDialogListener listener;


    public CommonAlertDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CommonAlertDialog(Context context, Builder builder) {
        super(context);
        this.context = builder.context;
        this.title = builder.title;
        this.content = builder.content;
        this.leftText = builder.leftText;
        this.rightText = builder.rightText;
        this.listener = builder.listener;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.common_dialog_alert;
    }

    @Override
    protected void initView() {
        mCommonDialogTitleTv = (TextView) findViewById(R.id.common_alert_dialog_title_tv);
        mCommonDialogContentTv = (TextView) findViewById(R.id.common_alert_dialog_content_tv);
        mCommonDialogCancelTv = (TextView) findViewById(R.id.common_alert_dialog_cancel_tv);
        mCommonDialogConfirmTv = (TextView) findViewById(R.id.common_alert_dialog_confirm_tv);
    }

    @Override
    protected void initData() {

        if (!TextUtils.isEmpty(title)) {
            mCommonDialogTitleTv.setVisibility(View.VISIBLE);
            mCommonDialogTitleTv.setText("" + title);
        }

        if (!TextUtils.isEmpty(content)) {
            mCommonDialogContentTv.setVisibility(View.VISIBLE);
            mCommonDialogContentTv.setText("" + content);
        }

        if (!TextUtils.isEmpty(leftText)) {
            mCommonDialogCancelTv.setVisibility(View.VISIBLE);
            mCommonDialogCancelTv.setText("" + leftText);
        }

        if (!TextUtils.isEmpty(rightText)) {
            mCommonDialogConfirmTv.setVisibility(View.VISIBLE);
            mCommonDialogConfirmTv.setText("" + rightText);
        }

        mCommonDialogCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.cancel();
                    dismiss();
                }
            }
        });

        mCommonDialogConfirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.confirm(null);
                    dismiss();
                }
            }
        });
    }

    public static class Builder {
        private String title;
        private String content;
        private String leftText;
        private String rightText;
        private Context context;
        private ICrazyBaseDialogListener listener;

        public Builder with(Context context) {
            this.context = context;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }


        public Builder setContent(String content) {
            this.content = content;
            return this;
        }


        public Builder setLeftText(String leftText) {
            this.leftText = leftText;
            return this;
        }


        public Builder setRightText(String rightText) {
            this.rightText = rightText;
            return this;
        }

        public Builder setOnDialogListener(ICrazyBaseDialogListener listener) {
            this.listener = listener;
            return this;
        }

        public CommonAlertDialog build() {
            return new CommonAlertDialog(context, this);
        }
    }


}
