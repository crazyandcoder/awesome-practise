package com.crazyandcoder.android.lib.common.widget.gridview;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName: GridSpacingItemDecoration
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/14 10:50 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/14 10:50 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    //列数
    private int spacing;
    //间隔
    private boolean includeEdge;
    //是否包含边缘

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //这里是关键，需要根据你有几列来判断
        int position = parent.getChildAdapterPosition(view);
        // item position
        int column = position % spanCount;
        // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount;
            // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount;
            // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) {
                // top edge
                outRect.top = spacing;
            }
            outRect.bottom = spacing;
            // item bottom
        } else {
            outRect.left = column * spacing / spanCount;
            // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing / spanCount;
            // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = spacing;
                // item top
            }
        }
    }
}