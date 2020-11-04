package com.crazyandcoder.android.lib.base.mvp;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.crazyandcoder.android.lib.base.utils.CrazyUtils;

import static com.crazyandcoder.android.lib.base.utils.Preconditions.checkNotNull;

/**
 * @ClassName: IView
 * @Description: The root view interface for every mvp view
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/21 6:35 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/21 6:35 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ICrazyView {

    /**
     * 显示加载
     */
    default void showLoading() {

    }

    /**
     * 隐藏加载
     */
    default void hideLoading() {

    }

    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 {@code null}
     */
    void showMessage(@NonNull String message);

}
