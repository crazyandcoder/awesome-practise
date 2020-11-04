package com.crazyandcoder.android.lib.common.widget.bottomtab.item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ClassName: BaseTabItem
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 9:36 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 9:36 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class AbsBaseTabItem extends FrameLayout {

    public AbsBaseTabItem(@NonNull Context context) {
        super(context);
    }

    public AbsBaseTabItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsBaseTabItem(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置选中状态
     *
     * @param checked
     */
    abstract public void setChecked(boolean checked);

    /**
     * 设置消息数字。注意：一般数字需要大于0才会显示
     *
     * @param number
     */
    abstract public void setMessageNumber(int number);

    /**
     * 设置是否显示无数字的小圆点。注意：如果消息数字不为0，优先显示带数字的圆
     *
     * @param hasMessage
     */
    abstract public void setHasMessage(boolean hasMessage);

    /**
     * 设置标题
     *
     * @param title
     */
    abstract public void setTitle(String title);

    /**
     * 设置未选中状态下的图标
     *
     * @param drawable
     */
    abstract public void setDefaultDrawable(Drawable drawable);

    /**
     * 设置选中状态下的图标
     *
     * @param drawable
     */
    abstract public void setSelectedDrawable(Drawable drawable);

    /**
     * 获取标题文字
     *
     * @return
     */
    abstract public String getTitle();

    /**
     * 已选中的状态下再次点击时触发
     */
    public void onRepeat() {
    }

}
