package com.crazyandcoder.android.lib.common.widget.bottomtab;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.crazyandcoder.android.lib.common.R;
import com.crazyandcoder.android.lib.common.widget.bottomtab.config.MaterialMode;
import com.crazyandcoder.android.lib.common.widget.bottomtab.controller.IBottomLayoutController;
import com.crazyandcoder.android.lib.common.widget.bottomtab.controller.ItemController;
import com.crazyandcoder.android.lib.common.widget.bottomtab.controller.NavigationController;
import com.crazyandcoder.android.lib.common.widget.bottomtab.item.AbsBaseTabItem;
import com.crazyandcoder.android.lib.common.widget.bottomtab.item.OnlyIconMaterialItemView;
import com.crazyandcoder.android.lib.common.widget.bottomtab.layout.CustomItemLayout;
import com.crazyandcoder.android.lib.common.widget.bottomtab.layout.CustomItemVerticalLayout;
import com.crazyandcoder.android.lib.common.widget.bottomtab.layout.MaterialItemLayout;
import com.crazyandcoder.android.lib.common.widget.bottomtab.layout.MaterialItemVerticalLayout;
import com.crazyandcoder.android.lib.common.widget.bottomtab.listener.OnTabItemSelectedListener;
import com.crazyandcoder.android.lib.common.widget.bottomtab.listener.ViewPagerPageChangeListener;
import com.crazyandcoder.android.lib.common.widget.bottomtab.utils.Utils;
import com.crazyandcoder.android.lib.common.widget.bottomtab.item.MaterialItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PageBottomTabView
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 7:53 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 7:53 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyPageBottomTabView extends ViewGroup {

    private int mTabPaddingTop;
    private int mTabPaddingBottom;

    private NavigationController mNavigationController;

    private ViewPagerPageChangeListener mPageChangeListener;
    private ViewPager mViewPager;

    private boolean mEnableVerticalLayout;

    private OnTabItemSelectedListener mTabItemListener = new OnTabItemSelectedListener() {
        @Override
        public void onSelected(int index, int old) {
            if (mViewPager != null) {
                mViewPager.setCurrentItem(index, false);
            }
        }

        @Override
        public void onRepeat(int index) {
        }
    };

    public CrazyPageBottomTabView(Context context) {
        super(context);
    }

    public CrazyPageBottomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CrazyPageBottomTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);

    }

    /**
     * 设置自定义属性
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        mPageChangeListener = new ViewPagerPageChangeListener(mNavigationController);
        setPadding(0, 0, 0, 0);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.CommonPageBottomTabView);
        if (a.hasValue(R.styleable.CommonPageBottomTabView_CommonNavigationPaddingTop)) {
            mTabPaddingTop = a.getDimensionPixelSize(R.styleable.CommonPageBottomTabView_CommonNavigationPaddingTop, 0);
        }
        if (a.hasValue(R.styleable.CommonPageBottomTabView_CommonNavigationPaddingBottom)) {
            mTabPaddingBottom = a.getDimensionPixelSize(R.styleable.CommonPageBottomTabView_CommonNavigationPaddingBottom, 0);
        }
        a.recycle();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int count = getChildCount();

        int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
        int maxHeight = MeasureSpec.getSize(heightMeasureSpec);

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
        }

        setMeasuredDimension(maxWidth, maxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        final int width = r - l;
        final int height = b - t;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            child.layout(0, 0, width, height);
        }
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return CrazyPageBottomTabView.class.getName();
    }

    /**
     * 构建 Material Desgin 风格的导航栏
     */
    public MaterialBuilder material() {
        return new MaterialBuilder();
    }

    /**
     * 构建 Material Desgin 风格的导航栏
     */
    public class MaterialBuilder {

        private final int DEFAULT_COLOR = 0x56000000;

        private List<MaterialItemViewData> itemDatas;
        private int defaultColor;
        private int mode;
        private int messageBackgroundColor;
        private int messageNumberColor;
        private boolean enableVerticalLayout = false;
        private boolean doTintIcon = true;
        private boolean animateLayoutChanges = false;

        MaterialBuilder() {
            itemDatas = new ArrayList<>();
        }

        /**
         * 完成构建
         *
         * @return {@link NavigationController},通过它进行后续操作
         * @throws RuntimeException 没有添加导航项时会抛出
         */
        @NonNull
        public NavigationController build() {
            mEnableVerticalLayout = enableVerticalLayout;

            // 未添加任何按钮
            if (itemDatas.isEmpty()) {
                throw new RuntimeException("must add a navigation item");
            }

            // 设置默认颜色
            if (defaultColor == 0) {
                defaultColor = DEFAULT_COLOR;
            }

            ItemController itemController;

            if (enableVerticalLayout) {//垂直布局

                List<AbsBaseTabItem> items = new ArrayList<>();

                for (MaterialItemViewData data : itemDatas) {

                    OnlyIconMaterialItemView materialItemView = new OnlyIconMaterialItemView(getContext());
                    materialItemView.initialization(data.title, data.drawable, data.checkedDrawable, doTintIcon, defaultColor, data.chekedColor);

                    //检查是否设置了消息圆点的颜色
                    if (messageBackgroundColor != 0) {
                        materialItemView.setMessageBackgroundColor(messageBackgroundColor);
                    }

                    //检查是否设置了消息数字的颜色
                    if (messageNumberColor != 0) {
                        materialItemView.setMessageNumberColor(messageNumberColor);
                    }

                    items.add(materialItemView);
                }

                MaterialItemVerticalLayout materialItemVerticalLayout = new MaterialItemVerticalLayout(getContext());
                materialItemVerticalLayout.initialize(items, animateLayoutChanges, doTintIcon, defaultColor);
                materialItemVerticalLayout.setPadding(0, mTabPaddingTop, 0, mTabPaddingBottom);

                CrazyPageBottomTabView.this.removeAllViews();
                CrazyPageBottomTabView.this.addView(materialItemVerticalLayout);

                itemController = materialItemVerticalLayout;

            } else {//水平布局

                boolean changeBackground = (mode & MaterialMode.CHANGE_BACKGROUND_COLOR) > 0;

                List<MaterialItemView> items = new ArrayList<>();
                List<Integer> checkedColors = new ArrayList<>();

                for (MaterialItemViewData data : itemDatas) {
                    // 记录设置的选中颜色
                    checkedColors.add(data.chekedColor);

                    MaterialItemView materialItemView = new MaterialItemView(getContext());
                    // 初始化Item,需要切换背景颜色就将选中颜色改成白色
                    materialItemView.initialization(data.title, data.drawable, data.checkedDrawable, doTintIcon, defaultColor, changeBackground ? Color.WHITE : data.chekedColor);

                    //检查是否设置了消息圆点的颜色
                    if (messageBackgroundColor != 0) {
                        materialItemView.setMessageBackgroundColor(messageBackgroundColor);
                    }

                    //检查是否设置了消息数字的颜色
                    if (messageNumberColor != 0) {
                        materialItemView.setMessageNumberColor(messageNumberColor);
                    }

                    items.add(materialItemView);
                }

                MaterialItemLayout materialItemLayout = new MaterialItemLayout(getContext());
                materialItemLayout.initialize(items, checkedColors, mode, animateLayoutChanges, doTintIcon, defaultColor);
                materialItemLayout.setPadding(0, mTabPaddingTop, 0, mTabPaddingBottom);

                CrazyPageBottomTabView.this.removeAllViews();
                CrazyPageBottomTabView.this.addView(materialItemLayout);

                itemController = materialItemLayout;
            }

            mNavigationController = new NavigationController(new Controller(), itemController);
            mNavigationController.addTabItemSelectedListener(mTabItemListener);

            return mNavigationController;
        }

        /**
         * 添加一个导航按钮
         *
         * @param drawableRes 图标资源
         * @param title       显示文字内容.尽量简短
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder addItem(@DrawableRes int drawableRes, @NonNull String title) {
            addItem(drawableRes, drawableRes, title, Utils.getColorPrimary(getContext()));
            return MaterialBuilder.this;
        }

        /**
         * 添加一个导航按钮
         *
         * @param drawableRes        图标资源
         * @param checkedDrawableRes 选中时的图标资源
         * @param title              显示文字内容.尽量简短
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder addItem(@DrawableRes int drawableRes, @DrawableRes int checkedDrawableRes, @NonNull String title) {
            addItem(drawableRes, checkedDrawableRes, title, Utils.getColorPrimary(getContext()));
            return MaterialBuilder.this;
        }

        /**
         * 添加一个导航按钮
         *
         * @param drawableRes 图标资源
         * @param title       显示文字内容.尽量简短
         * @param chekedColor 选中的颜色
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder addItem(@DrawableRes int drawableRes, @NonNull String title, @ColorInt int chekedColor) {
            addItem(drawableRes, drawableRes, title, chekedColor);
            return MaterialBuilder.this;
        }

        /**
         * 添加一个导航按钮
         *
         * @param drawableRes        图标资源
         * @param checkedDrawableRes 选中时的图标资源
         * @param title              显示文字内容.尽量简短
         * @param chekedColor        选中的颜色
         * @return {@link MaterialBuilder}
         * @throws Resources.NotFoundException drawable 资源获取异常
         */
        public MaterialBuilder addItem(@DrawableRes int drawableRes, @DrawableRes int checkedDrawableRes, @NonNull String title, @ColorInt int chekedColor) {
            Drawable defaultDrawable = ContextCompat.getDrawable(getContext(), drawableRes);
            Drawable checkDrawable = ContextCompat.getDrawable(getContext(), checkedDrawableRes);
            if (defaultDrawable == null) {
                throw new Resources.NotFoundException("Resource ID " + Integer.toHexString(drawableRes));
            }
            if (checkDrawable == null) {
                throw new Resources.NotFoundException("Resource ID " + Integer.toHexString(checkedDrawableRes));
            }
            addItem(defaultDrawable, checkDrawable, title, chekedColor);
            return MaterialBuilder.this;
        }

        /**
         * 添加一个导航按钮
         *
         * @param drawable 图标资源
         * @param title    显示文字内容.尽量简短
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder addItem(@NonNull Drawable drawable, @NonNull String title) {
            addItem(drawable, drawable, title, Utils.getColorPrimary(getContext()));
            return MaterialBuilder.this;
        }

        /**
         * 添加一个导航按钮
         *
         * @param drawable        图标资源
         * @param checkedDrawable 选中时的图标资源
         * @param title           显示文字内容.尽量简短
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder addItem(@NonNull Drawable drawable, @NonNull Drawable checkedDrawable, @NonNull String title) {
            addItem(drawable, checkedDrawable, title, Utils.getColorPrimary(getContext()));
            return MaterialBuilder.this;
        }

        /**
         * 添加一个导航按钮
         *
         * @param drawable    图标资源
         * @param title       显示文字内容.尽量简短
         * @param chekedColor 选中的颜色
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder addItem(@NonNull Drawable drawable, @NonNull String title, @ColorInt int chekedColor) {
            addItem(drawable, drawable, title, chekedColor);
            return MaterialBuilder.this;
        }

        /**
         * 添加一个导航按钮
         *
         * @param drawable        图标资源
         * @param checkedDrawable 选中时的图标资源
         * @param title           显示文字内容.尽量简短
         * @param chekedColor     选中的颜色
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder addItem(@NonNull Drawable drawable, @NonNull Drawable checkedDrawable, @NonNull String title, @ColorInt int chekedColor) {
            MaterialItemViewData data = new MaterialItemViewData();
            data.drawable = Utils.newDrawable(drawable);
            data.checkedDrawable = Utils.newDrawable(checkedDrawable);
            data.title = title;
            data.chekedColor = chekedColor;
            itemDatas.add(data);
            return MaterialBuilder.this;
        }

        /**
         * 设置导航按钮的默认（未选中状态）颜色
         *
         * @param color 16进制整形表示的颜色，例如红色：0xFFFF0000
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder setDefaultColor(@ColorInt int color) {
            defaultColor = color;
            return MaterialBuilder.this;
        }

        /**
         * 设置消息圆点的颜色
         *
         * @param color 16进制整形表示的颜色，例如红色：0xFFFF0000
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder setMessageBackgroundColor(@ColorInt int color) {
            messageBackgroundColor = color;
            return MaterialBuilder.this;
        }

        /**
         * 设置消息数字的颜色
         *
         * @param color 16进制整形表示的颜色，例如红色：0xFFFF0000
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder setMessageNumberColor(@ColorInt int color) {
            messageNumberColor = color;
            return MaterialBuilder.this;
        }

        /**
         * 设置模式(在垂直布局中无效)。默认文字一直显示，且背景色不变。
         * 可以通过{@link MaterialMode}选择模式。
         * <p>例如:</p>
         * {@code MaterialMode.HIDE_TEXT}
         * <p>或者多选:</p>
         * {@code MaterialMode.HIDE_TEXT | MaterialMode.CHANGE_BACKGROUND_COLOR}
         *
         * @param mode {@link MaterialMode}
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder setMode(int mode) {
            MaterialBuilder.this.mode = mode;
            return MaterialBuilder.this;
        }

        /**
         * 使用垂直布局
         *
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder enableVerticalLayout() {
            enableVerticalLayout = true;
            return MaterialBuilder.this;
        }

        /**
         * 不对图标进行染色
         *
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder dontTintIcon() {
            doTintIcon = false;
            return MaterialBuilder.this;
        }

        /**
         * 通过{@link NavigationController}动态移除/添加导航项时,显示默认的布局动画
         *
         * @return {@link MaterialBuilder}
         */
        public MaterialBuilder enableAnimateLayoutChanges() {
            animateLayoutChanges = true;
            return MaterialBuilder.this;
        }

    }

    /**
     * 材料设计的单项视图信息
     */
    private static class MaterialItemViewData {
        Drawable drawable;
        Drawable checkedDrawable;
        String title;
        @ColorInt
        int chekedColor;
    }



    /**
     * 构建自定义导航栏
     */
    public CustomBuilder custom() {
        return new CustomBuilder();
    }

    /**
     * 构建 自定义 的导航栏
     */
    public class CustomBuilder {

        private List<AbsBaseTabItem> items;

        private boolean enableVerticalLayout = false;
        private boolean animateLayoutChanges = false;

        CustomBuilder() {
            items = new ArrayList<>();
        }

        /**
         * 完成构建
         *
         * @return {@link NavigationController},通过它进行后续操作
         * @throws RuntimeException 没有添加导航项时会抛出
         */
        public NavigationController build() {

            mEnableVerticalLayout = enableVerticalLayout;

            //未添加任何按钮
            if (items.size() == 0) {
                throw new RuntimeException("must add a navigation item");
            }

            ItemController itemController;

            if (enableVerticalLayout) {//垂直布局
                CustomItemVerticalLayout verticalItemLayout = new CustomItemVerticalLayout(getContext());
                verticalItemLayout.initialize(items, animateLayoutChanges);
                verticalItemLayout.setPadding(0, mTabPaddingTop, 0, mTabPaddingBottom);

                CrazyPageBottomTabView.this.removeAllViews();
                CrazyPageBottomTabView.this.addView(verticalItemLayout);
                itemController = verticalItemLayout;
            } else {//水平布局
                CustomItemLayout customItemLayout = new CustomItemLayout(getContext());
                customItemLayout.initialize(items, animateLayoutChanges);
                customItemLayout.setPadding(0, mTabPaddingTop, 0, mTabPaddingBottom);

                CrazyPageBottomTabView.this.removeAllViews();
                CrazyPageBottomTabView.this.addView(customItemLayout);
                itemController = customItemLayout;
            }

            mNavigationController = new NavigationController(new Controller(), itemController);
            mNavigationController.addTabItemSelectedListener(mTabItemListener);

            return mNavigationController;
        }

        /**
         * 添加一个导航按钮
         *
         * @param baseTabItem {@link AbsBaseTabItem},所有自定义Item都必须继承它
         * @return {@link CustomBuilder}
         */
        public CustomBuilder addItem(AbsBaseTabItem baseTabItem) {
            items.add(baseTabItem);
            return CustomBuilder.this;
        }

        /**
         * 使用垂直布局
         *
         * @return {@link CustomBuilder}
         */
        public CustomBuilder enableVerticalLayout() {
            enableVerticalLayout = true;
            return CustomBuilder.this;
        }

        /**
         * 通过{@link NavigationController}动态移除/添加导航项时,显示默认的布局动画
         *
         * @return {@link CustomBuilder}
         */
        public CustomBuilder enableAnimateLayoutChanges() {
            animateLayoutChanges = true;
            return CustomBuilder.this;
        }
    }



    /**
     * 实现控制接口
     */
    private class Controller implements IBottomLayoutController {

        private ObjectAnimator animator;
        private boolean hide = false;

        @Override
        public void setupWithViewPager(ViewPager viewPager) {
            if (viewPager == null) {
                return;
            }

            mViewPager = viewPager;

            if (mPageChangeListener != null) {
                mViewPager.removeOnPageChangeListener(mPageChangeListener);
            } else {
                mPageChangeListener = new ViewPagerPageChangeListener(mNavigationController);
            }

            if (mNavigationController != null) {
                int n = mViewPager.getCurrentItem();
                if (mNavigationController.getSelected() != n) {
                    mNavigationController.setSelect(n);
                }
                mViewPager.addOnPageChangeListener(mPageChangeListener);
            }
        }

        @Override
        public void hideBottomLayout() {
            if (!hide) {
                hide = true;
                getAnimator().start();
            }
        }

        @Override
        public void showBottomLayout() {
            if (hide) {
                hide = false;
                getAnimator().reverse();
            }
        }

        private ObjectAnimator getAnimator() {
            if (animator == null) {
                if (mEnableVerticalLayout) {// 垂直布局向左隐藏
                    animator = ObjectAnimator.ofFloat(
                            CrazyPageBottomTabView.this, "translationX", 0, -CrazyPageBottomTabView.this.getWidth());
                } else {// 水平布局向下隐藏
                    animator = ObjectAnimator.ofFloat(
                            CrazyPageBottomTabView.this, "translationY", 0, CrazyPageBottomTabView.this.getHeight());
                }
                animator.setDuration(300);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
            }
            return animator;
        }
    }


    private static final String INSTANCE_STATUS = "INSTANCE_STATUS";
    private final String STATUS_SELECTED = "STATUS_SELECTED";

    @Override
    protected Parcelable onSaveInstanceState() {
        if (mNavigationController == null) {
            return super.onSaveInstanceState();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putInt(STATUS_SELECTED, mNavigationController.getSelected());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            int selected = bundle.getInt(STATUS_SELECTED, 0);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));

            if (selected != 0 && mNavigationController != null) {
                mNavigationController.setSelect(selected);
            }

            return;
        }
        super.onRestoreInstanceState(state);
    }

}
