package com.crazyandcoder.android.lib.common.widget.pushlayout;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazyandcoder.android.lib.common.utils.log.CrazyLog;
import com.crazyandcoder.android.lib.common.utils.screen.CrazyScreenInfoUtils;

/**
 * @ClassName: AbsSwipeLayout
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/29 10:20 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/29 10:20 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class AbsSwipeLayout extends LinearLayout {

    public static final int DIRECTION_ALL = 0;
    public static final int DIRECTION_LEFT = DIRECTION_ALL + 1;
    public static final int DIRECTION_RIGHT = DIRECTION_LEFT + 1;
    public static final int DIRECTION_TOP = DIRECTION_RIGHT + 1;
    public static final int DIRECTION_BOTTOM = DIRECTION_TOP + 1;

    private GestureDetector mDetector;

    private onSwipeListener mOnSwipeListener;

    public AbsSwipeLayout(@NonNull Context context) {
        this(context, null);
    }

    public AbsSwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbsSwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs,
                          int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(getLayout(), this);
        initView(inflate);
        mDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                float x = e.getX();
                float y = e.getY();
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (mOnSwipeListener != null) {
                    mOnSwipeListener.onClickNotification();
                }
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                float x1 = e1.getX();
                float x2 = e2.getX();
                float y1 = e1.getY();
                float y2 = e2.getY();
                int offsetX = (int) (x2 - x1);
                int offsetY = (int) (y2 - y1);

                ScrollerToSwipeView(getSwipeDirection(), offsetX, offsetY);

                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
    }

    /**
     * 设置指定的方向上面进行滑动消失
     *
     * @param swipeDirection
     * @param offsetX
     * @param offsetY
     */
    private void ScrollerToSwipeView(int swipeDirection, int offsetX, int offsetY) {
        switch (swipeDirection) {


            case DIRECTION_ALL:
                layoutWithAlpha(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
                break;

            case DIRECTION_LEFT:
                if (offsetX <= 0) {
                    layoutWithAlpha(getLeft() + offsetX, getTop(), getRight() + offsetX, getBottom());
                }
                break;

            case DIRECTION_RIGHT:
                if (offsetX > 0) {
                    layoutWithAlpha(getLeft() + offsetX, getTop(), getRight() + offsetX, getBottom());
                }
                break;

            case DIRECTION_TOP:
                if (offsetY <= 0) {
                    layoutWithAlpha(getLeft(), getTop() + offsetY, getRight() + offsetX, getBottom());
                }
                break;

            case DIRECTION_BOTTOM:
                if (offsetY > 0) {
                    layoutWithAlpha(getLeft(), getTop() + offsetY, getRight() + offsetX, getBottom());
                }
                break;

            default:
                break;
        }


    }


    /**
     * init view
     *
     * @param view
     */
    public abstract void initView(View view);


    /**
     * 开启动画
     *
     * @param view
     */
    private void startAnimation(View view) {
    }


    /**
     * 滑动删除的方向
     * <p>
     * DIRECTION_LEFT 向左滑动删除
     * DIRECTION_RIGHT 向右滑动删除
     * DIRECTION_TOP 向上滑动删除
     * DIRECTION_BOTTOM 向下滑动删除
     * DIRECTION_ALL 向下滑动删除
     *
     * @return
     */
    public abstract int getSwipeDirection();

    /**
     * 返回每个子类具体的布局
     *
     * @return
     */
    public abstract int getLayout();

    /**
     * 在view scroll的时候重新layout另外设置alpha
     *
     * @param l
     * @param t
     * @param r
     * @param b
     */
    private void layoutWithAlpha(int l, int t, int r, int b) {
        layout(l, t, r, b);
        float alpha = (1080f - Math.abs(getLeft())) / 1080f;
        setAlpha(alpha);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean detectedUp = event.getAction() == MotionEvent.ACTION_UP;
        if (!mDetector.onTouchEvent(event) && detectedUp) {
            if (mOnSwipeListener != null) {
                mOnSwipeListener.onClickNotification();
            }
        }
        return true;
    }


    /**
     * 点击和消失的回调
     *
     * @param listener
     */
    public void setOnSwipeListener(onSwipeListener listener) {
        mOnSwipeListener = listener;
    }

    /**
     * 点击和消失的回调
     */
    public interface onSwipeListener {

        /**
         * 通知滑动出去消失了的回调
         */
        void onDisappear();

        /**
         * 点击通知的回调
         */
        void onClickNotification();
    }
}
