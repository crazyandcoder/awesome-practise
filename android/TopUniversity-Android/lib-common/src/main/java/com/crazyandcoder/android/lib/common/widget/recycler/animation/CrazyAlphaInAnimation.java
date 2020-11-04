package com.crazyandcoder.android.lib.common.widget.recycler.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * @ClassName: CrazyAlphaInAnimation
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/18 6:19 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/18 6:19 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyAlphaInAnimation implements ICrazyBaseAnimation {
    private static final float DEFAULT_ALPHA_FROM = 0f;
    private final float mFrom;

    public CrazyAlphaInAnimation() {
        this(DEFAULT_ALPHA_FROM);
    }

    public CrazyAlphaInAnimation(float from) {
        mFrom = from;
    }

    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", mFrom, 1f)};
    }
}
