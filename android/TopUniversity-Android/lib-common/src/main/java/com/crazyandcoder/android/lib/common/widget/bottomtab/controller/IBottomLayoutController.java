package com.crazyandcoder.android.lib.common.widget.bottomtab.controller;

import androidx.viewpager.widget.ViewPager;

/**
 * @ClassName: IBottomLayoutController
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 9:57 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 9:57 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface IBottomLayoutController {

    /**
     * 方便适配ViewPager页面切换<p>
     * 注意：ViewPager页面数量必须等于导航栏的Item数量
     *
     * @param viewPager {@link ViewPager}
     */
    void setupWithViewPager(ViewPager viewPager);

    /**
     * 向下移动隐藏导航栏
     */
    void hideBottomLayout();

    /**
     * 向上移动显示导航栏
     */
    void showBottomLayout();
}
