package com.crazyandcoder.android.lib.common.widget.recycler.diff;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListUpdateCallback;

import com.crazyandcoder.android.lib.common.widget.recycler.AbsCrazyBaseAdapter;

/**
 * @ClassName: AbsCrazyBaseAdapterListUpdateCallback
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/18 6:23 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/18 6:23 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AbsCrazyBaseAdapterListUpdateCallback implements ListUpdateCallback {

    @NonNull
    private final AbsCrazyBaseAdapter mAdapter;

    public AbsCrazyBaseAdapterListUpdateCallback(@NonNull AbsCrazyBaseAdapter adapter) {
        this.mAdapter = adapter;
    }

    @Override
    public void onInserted(int position, int count) {
        this.mAdapter.notifyItemRangeInserted(position + mAdapter.getHeaderLayoutCount(), count);
    }

    @Override
    public void onRemoved(int position, int count) {
        this.mAdapter.notifyItemRangeRemoved(position + mAdapter.getHeaderLayoutCount(), count);
    }

    @Override
    public void onMoved(int fromPosition, int toPosition) {
        this.mAdapter.notifyItemMoved(fromPosition + mAdapter.getHeaderLayoutCount(), toPosition + mAdapter.getHeaderLayoutCount());
    }

    @Override
    public void onChanged(int position, int count, @Nullable Object payload) {
        this.mAdapter.notifyItemRangeChanged(position + mAdapter.getHeaderLayoutCount(), count, payload);
    }
}
