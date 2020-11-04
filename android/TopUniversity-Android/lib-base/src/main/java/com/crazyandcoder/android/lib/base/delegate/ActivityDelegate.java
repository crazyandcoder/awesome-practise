package com.crazyandcoder.android.lib.base.delegate;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ClassName: ActivityDelegate
 * @Description: {@link Activity} 代理类,用于框架内部在每个 {@link Activity} 的对应生命周期中插入需要的逻辑
 * @see ActivityDelegateImpl
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/11 6:20 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/11 6:20 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ActivityDelegate {

    String LAYOUT_LINEARLAYOUT = "LinearLayout";
    String LAYOUT_FRAMELAYOUT = "FrameLayout";
    String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    String ACTIVITY_DELEGATE = "ACTIVITY_DELEGATE";

    void onCreate(@Nullable Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onSaveInstanceState(@NonNull Bundle outState);

    void onDestroy();

}
