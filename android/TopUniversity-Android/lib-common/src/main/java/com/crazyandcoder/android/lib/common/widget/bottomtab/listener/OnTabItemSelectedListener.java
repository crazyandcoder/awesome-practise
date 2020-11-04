package com.crazyandcoder.android.lib.common.widget.bottomtab.listener;

/**
 * @ClassName: OnTabItemSelectedListener
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/26 9:35 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/26 9:35 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface OnTabItemSelectedListener {

    /**
     * 选中导航栏的某一项
     *
     * @param index 索引导航按钮，按添加顺序排序
     * @param old   前一个选中项，如果没有就等于-1
     */
    void onSelected(int index, int old);

    /**
     * 重复选中
     *
     * @param index 索引导航按钮，按添加顺序排序
     */
    void onRepeat(int index);
}
