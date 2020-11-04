package com.crazyandcoder.android.lib.common.widget.dialog;

public interface ICrazyBaseDialogListener<T> {

    /**
     * 取消
     */
    void cancel();

    /**
     * 确认
     *
     * @param t
     */
    void confirm(T t);
}
