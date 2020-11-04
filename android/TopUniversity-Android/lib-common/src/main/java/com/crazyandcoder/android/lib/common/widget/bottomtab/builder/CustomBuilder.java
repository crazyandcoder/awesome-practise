//package com.crazyandcoder.android.lib.common.widget.bottomtab.builder;
//
//import com.crazyandcoder.android.lib.common.widget.bottomtab.PageBottomTabView;
//import com.crazyandcoder.android.lib.common.widget.bottomtab.controller.ItemController;
//import com.crazyandcoder.android.lib.common.widget.bottomtab.controller.NavigationController;
//import com.crazyandcoder.android.lib.common.widget.bottomtab.item.AbsBaseTabItem;
//import com.crazyandcoder.android.lib.common.widget.bottomtab.layout.CustomItemLayout;
//import com.crazyandcoder.android.lib.common.widget.bottomtab.layout.CustomItemVerticalLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @ClassName: CustomBuilder
// * @Description: java类作用描述
// * @Author: crazyandcoder
// * @email: lijiwork@sina.com
// * @CreateDate: 2020/5/26 10:13 AM
// * @UpdateUser: 更新者
// * @UpdateDate: 2020/5/26 10:13 AM
// * @UpdateRemark: 更新说明
// * @Version: 1.0
// */
//public class CustomBuilder extends AbsBuilder {
//
//    private List<AbsBaseTabItem> items;
//    private PageBottomTabView mPageBottomTabView;
//    private boolean mEnableVerticalLayout = false;
//    private boolean animateLayoutChanges = false;
//    private int mTabPaddingTop;
//    private int mTabPaddingBottom;
//
//    CustomBuilder(PageBottomTabView tabView, boolean enableVerticalLayout) {
//        items = new ArrayList<>();
//        this.mPageBottomTabView = tabView;
//        this.mEnableVerticalLayout = enableVerticalLayout;
//    }
//
//    public int getTabPaddingTop() {
//        return mTabPaddingTop;
//    }
//
//    public void setTabPaddingTop(int mTabPaddingTop) {
//        this.mTabPaddingTop = mTabPaddingTop;
//    }
//
//    public int getTabPaddingBottom() {
//        return mTabPaddingBottom;
//    }
//
//    public void setTabPaddingBottom(int mTabPaddingBottom) {
//        this.mTabPaddingBottom = mTabPaddingBottom;
//    }
//
//    /**
//     * 完成构建
//     *
//     * @return {@link NavigationController},通过它进行后续操作
//     * @throws RuntimeException 没有添加导航项时会抛出
//     */
//    public NavigationController build() {
//
////        mEnableVerticalLayout = enableVerticalLayout;
//
//        //未添加任何按钮
//        if (items.size() == 0) {
//            throw new RuntimeException("must add a navigation item");
//        }
//
//        ItemController itemController;
//
//        //垂直布局
//        if (mEnableVerticalLayout) {
//            CustomItemVerticalLayout verticalItemLayout = new CustomItemVerticalLayout(mPageBottomTabView.getContext());
//            verticalItemLayout.initialize(items, animateLayoutChanges);
//            verticalItemLayout.setPadding(0, mTabPaddingTop, 0, mTabPaddingBottom);
//
//            mPageBottomTabView.removeAllViews();
//            mPageBottomTabView.addView(verticalItemLayout);
//            itemController = verticalItemLayout;
//        } else {//水平布局
//            CustomItemLayout customItemLayout = new CustomItemLayout(mPageBottomTabView.getContext());
//            customItemLayout.initialize(items, animateLayoutChanges);
//            customItemLayout.setPadding(0, mTabPaddingTop, 0, mTabPaddingBottom);
//
//            mPageBottomTabView.removeAllViews();
//            mPageBottomTabView.addView(customItemLayout);
//            itemController = customItemLayout;
//        }
//
//        mNavigationController = new NavigationController(new Controller(), itemController);
//        mNavigationController.addTabItemSelectedListener(mTabItemListener);
//
//        return mNavigationController;
//    }
//
//    /**
//     * 添加一个导航按钮
//     *
//     * @param baseTabItem {@link AbsBaseTabItem},所有自定义Item都必须继承它
//     * @return {@link CustomBuilder}
//     */
//    public CustomBuilder addItem(AbsBaseTabItem baseTabItem) {
//        items.add(baseTabItem);
//        return CustomBuilder.this;
//    }
//
//    /**
//     * 使用垂直布局
//     *
//     * @return {@link CustomBuilder}
//     */
//    public CustomBuilder enableVerticalLayout() {
//        enableVerticalLayout = true;
//        return CustomBuilder.this;
//    }
//
//    /**
//     * 通过{@link NavigationController}动态移除/添加导航项时,显示默认的布局动画
//     *
//     * @return {@link CustomBuilder}
//     */
//    public CustomBuilder enableAnimateLayoutChanges() {
//        animateLayoutChanges = true;
//        return CustomBuilder.this;
//    }
//
//
//}
