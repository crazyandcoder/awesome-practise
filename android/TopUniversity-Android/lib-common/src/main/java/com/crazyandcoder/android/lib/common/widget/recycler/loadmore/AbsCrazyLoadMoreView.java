package com.crazyandcoder.android.lib.common.widget.recycler.loadmore;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import com.crazyandcoder.android.lib.common.widget.recycler.CrazyBaseViewHolder;

/**
 * @ClassName: AbsCrazyLoadMoreView
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/18 6:05 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/18 6:05 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class AbsCrazyLoadMoreView {

    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_END = 4;

    private int mLoadMoreStatus = STATUS_DEFAULT;
    private boolean mLoadMoreEndGone = false;


    public void setLoadMoreStatus(int loadMoreStatus) {
        this.mLoadMoreStatus = loadMoreStatus;
    }

    public int getLoadMoreStatus() {
        return mLoadMoreStatus;
    }

    public void convert(CrazyBaseViewHolder holder) {
        switch (mLoadMoreStatus) {
            case STATUS_LOADING:
                visibleLoading(holder, true);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_FAIL:
                visibleLoading(holder, false);
                visibleLoadFail(holder, true);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_END:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, true);
                break;
            case STATUS_DEFAULT:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
            default:
                break;
        }
    }

    private void visibleLoading(CrazyBaseViewHolder holder, boolean visible) {
        holder.setGone(getLoadingViewId(), visible);
    }

    private void visibleLoadFail(CrazyBaseViewHolder holder, boolean visible) {
        holder.setGone(getLoadFailViewId(), visible);
    }

    private void visibleLoadEnd(CrazyBaseViewHolder holder, boolean visible) {
        final int loadEndViewId = getLoadEndViewId();
        if (loadEndViewId != 0) {
            holder.setGone(loadEndViewId, visible);
        }
    }

    public final void setLoadMoreEndGone(boolean loadMoreEndGone) {
        this.mLoadMoreEndGone = loadMoreEndGone;
    }

    public final boolean isLoadEndMoreGone() {
        if (getLoadEndViewId() == 0) {
            return true;
        }
        return mLoadMoreEndGone;
    }

    /**
     * No more data is hidden
     *
     * @return true for no more data hidden load more
     * @deprecated Use {@link BaseQuickAdapter#loadMoreEnd(boolean)} instead.
     */
    @Deprecated
    public boolean isLoadEndGone() {
        return mLoadMoreEndGone;
    }

    /**
     * load more layout
     *
     * @return
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * loading view
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadingViewId();

    /**
     * load fail view
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadFailViewId();

    /**
     * load end view, you can return 0
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadEndViewId();
}
