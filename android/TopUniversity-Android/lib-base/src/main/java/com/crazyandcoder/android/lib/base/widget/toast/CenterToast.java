package com.crazyandcoder.android.lib.base.widget.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.crazyandcoder.android.lib.base.R;


/**
 * @author liji
 */
public class CenterToast extends Toast {

    private TextView tvContent;
    private String text;
    private int duration;

    public CenterToast(Context context, String content) {
        this(context, content, Toast.LENGTH_SHORT);
    }

    public CenterToast(Context context, String content, int duration) {
        super(context);
        this.text = content;
        this.duration = duration;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.base_center_toast, null);
        setGravity(Gravity.CENTER, 0, 0);
        //setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvContent.setText("" + text);
        setDur(duration);
        setView(view);
    }

    public CenterToast setDur(int duration) {
        this.setDuration(duration);
        return this;
    }

}
