package com.crazyandcoder.android.lib.common.widget.recycler.animation;

import android.animation.Animator;
import android.view.View;

/**
 * @ClassName: AbsCrazyBaseAnimation
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/18 6:18 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/18 6:18 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ICrazyBaseAnimation {
    Animator[] getAnimators(View view);
}
