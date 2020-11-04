package com.crazyandcoder.android.lib.common.widget.bottomtab.controller;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.crazyandcoder.android.lib.common.widget.bottomtab.item.AbsBaseTabItem;
import com.crazyandcoder.android.lib.common.widget.bottomtab.listener.OnTabItemSelectedListener;
import com.crazyandcoder.android.lib.common.widget.bottomtab.listener.SimpleTabItemSelectedListener;
import com.crazyandcoder.android.lib.common.widget.bottomtab.utils.Utils;

/**
 * @ClassName: NavigationController
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 9:57 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 9:57 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class NavigationController implements ItemController,IBottomLayoutController {

    private IBottomLayoutController mBottomLayoutController;
    private ItemController mItemController;

    public NavigationController(IBottomLayoutController mBottomLayoutController, ItemController mItemController) {
        this.mBottomLayoutController = mBottomLayoutController;
        this.mItemController = mItemController;
    }

    @Override
    public void setSelect(int index) {
        mItemController.setSelect(index);
    }

    @Override
    public void setSelect(int index, boolean listener) {
        mItemController.setSelect(index, listener);
    }

    @Override
    public void setMessageNumber(int index, int number) {
        mItemController.setMessageNumber(index, number);
    }

    @Override
    public void setHasMessage(int index, boolean hasMessage) {
        mItemController.setHasMessage(index, hasMessage);
    }

    @Override
    public void addTabItemSelectedListener(@NonNull OnTabItemSelectedListener listener) {
        mItemController.addTabItemSelectedListener(listener);
    }

    @Override
    public void addSimpleTabItemSelectedListener(@NonNull SimpleTabItemSelectedListener listener) {
        mItemController.addSimpleTabItemSelectedListener(listener);
    }

    @Override
    public void setTitle(int index,@NonNull String title) {
        mItemController.setTitle(index, title);
    }

    @Override
    public void setDefaultDrawable(int index,@NonNull Drawable drawable) {
        mItemController.setDefaultDrawable(index, drawable);
    }

    @Override
    public void setSelectedDrawable(int index,@NonNull Drawable drawable) {
        mItemController.setSelectedDrawable(index, drawable);
    }

    @Override
    public int getSelected() {
        return mItemController.getSelected();
    }

    @Override
    public int getItemCount() {
        return mItemController.getItemCount();
    }

    @Override
    public String getItemTitle(int index) {
        return mItemController.getItemTitle(index);
    }

    @Override
    public boolean removeItem(int index) {
        return mItemController.removeItem(index);
    }

    @Override
    public void addMaterialItem(int index,@NonNull Drawable defaultDrawable,@NonNull Drawable selectedDrawable,@NonNull String title, int selectedColor) {
        mItemController.addMaterialItem(index, Utils.newDrawable(defaultDrawable), Utils.newDrawable(selectedDrawable), title, selectedColor);
    }

    @Override
    public void addCustomItem(int index,@NonNull AbsBaseTabItem item) {
        mItemController.addCustomItem(index, item);
    }

    @Override
    public void setupWithViewPager(@NonNull ViewPager viewPager) {
        mBottomLayoutController.setupWithViewPager(viewPager);
    }

    @Override
    public void hideBottomLayout() {
        mBottomLayoutController.hideBottomLayout();
    }

    @Override
    public void showBottomLayout() {
        mBottomLayoutController.showBottomLayout();
    }
}
