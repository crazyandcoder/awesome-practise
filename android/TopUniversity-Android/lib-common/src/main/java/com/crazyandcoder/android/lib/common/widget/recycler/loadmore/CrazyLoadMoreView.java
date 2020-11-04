package com.crazyandcoder.android.lib.common.widget.recycler.loadmore;

import com.crazyandcoder.android.lib.common.R;

/**
 * @ClassName: CrazyLoadMoreView
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/18 6:14 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/18 6:14 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyLoadMoreView extends AbsCrazyLoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.common_view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
