package com.crazyandcoder.android.lib.common.widget.bottomtab.config;

/**
 * @ClassName: MaterialMode
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 5:26 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 5:26 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

/**
 * <p>模式选择。</p>
 * <p>采用组合的形式，比如要两种效果可以这样做：</p>
 * MaterialMode.HIDE_TEXT | MaterialMode.CHANGE_BACKGROUND_COLOR
 */
public final class MaterialMode {
    /**
     * 隐藏文字内容，只在选中时显示文字
     */
    public static final int HIDE_TEXT = 0X1;

    /**
     * 开启导航栏背景变换。点击不同项切换不同背景颜色
     */
    public static final int CHANGE_BACKGROUND_COLOR = 0X2;

}
