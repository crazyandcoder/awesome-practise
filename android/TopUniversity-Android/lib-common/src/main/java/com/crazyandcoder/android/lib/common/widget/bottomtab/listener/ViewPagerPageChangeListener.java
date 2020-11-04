package com.crazyandcoder.android.lib.common.widget.bottomtab.listener;

import androidx.viewpager.widget.ViewPager;

import com.crazyandcoder.android.lib.common.widget.bottomtab.controller.NavigationController;

/**
 * @ClassName: ViewPagerPageChangeListener
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 10:05 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 10:05 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewPagerPageChangeListener implements ViewPager.OnPageChangeListener {

    private NavigationController mNavigationController;

    public ViewPagerPageChangeListener(NavigationController mNavigationController) {
        this.mNavigationController = mNavigationController;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (mNavigationController != null && mNavigationController.getSelected() != position) {
            mNavigationController.setSelect(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
