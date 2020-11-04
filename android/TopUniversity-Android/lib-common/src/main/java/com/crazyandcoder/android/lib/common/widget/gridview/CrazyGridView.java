package com.crazyandcoder.android.lib.common.widget.gridview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * @ClassName: CrazyGridView
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 6:33 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 6:33 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyGridView extends GridView {
    public CrazyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CrazyGridView(Context context) {
        super(context);
    }

    public CrazyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
