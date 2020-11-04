package com.crazyandcoder.android.lib.common.widget.bottomtab.view;

/**
 * @ClassName: RoundMessageView
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 5:22 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 5:22 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Locale;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.crazyandcoder.android.lib.common.R;
import com.crazyandcoder.android.lib.common.widget.bottomtab.utils.Utils;


public class RoundMessageView extends FrameLayout {
    private final View mOval;
    private final TextView mMessages;

    private int mMessageNumber;
    private boolean mHasMessage;

    public RoundMessageView(Context context) {
        this(context, null);
    }

    public RoundMessageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundMessageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.common_round_message_view, this, true);

        mOval = findViewById(R.id.oval);
        mMessages = findViewById(R.id.msg);
        mMessages.setTypeface(Typeface.DEFAULT_BOLD);
        mMessages.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
    }

    public void setMessageNumber(int number) {
        mMessageNumber = number;

        if (mMessageNumber > 0) {
            mOval.setVisibility(View.INVISIBLE);
            mMessages.setVisibility(View.VISIBLE);

            if (mMessageNumber < 10) {
                mMessages.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            } else {
                mMessages.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
            }

            if (mMessageNumber <= 99) {
                mMessages.setText(String.format(Locale.ENGLISH, "%d", mMessageNumber));
            } else {
                mMessages.setText(String.format(Locale.ENGLISH, "%d+", 99));
            }
        } else {
            mMessages.setVisibility(View.INVISIBLE);
            if (mHasMessage) {
                mOval.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setHasMessage(boolean hasMessage) {
        mHasMessage = hasMessage;

        if (hasMessage) {
            mOval.setVisibility(mMessageNumber > 0 ? View.INVISIBLE : View.VISIBLE);
        } else {
            mOval.setVisibility(View.INVISIBLE);
        }
    }

    public void tintMessageBackground(@ColorInt int color) {
        Drawable drawable = Utils.tinting(ContextCompat.getDrawable(getContext(), R.drawable.common_round), color);
        ViewCompat.setBackground(mOval, drawable);
        ViewCompat.setBackground(mMessages, drawable);
    }

    public void setMessageNumberColor(@ColorInt int color) {
        mMessages.setTextColor(color);
    }

    public int getMessageNumber() {
        return mMessageNumber;
    }

    public boolean hasMessage() {
        return mHasMessage;
    }


}

