package com.crazyandcoder.android.lib.common.widget.recycler.diff;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AbsCrazyBaseDiffCallback
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/18 6:22 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/18 6:22 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class AbsCrazyBaseDiffCallback <T> extends DiffUtil.Callback {

    private List<T> newList;
    private List<T> oldList;

    public AbsCrazyBaseDiffCallback(@Nullable List<T> newList) {
        this.newList = newList == null ? new ArrayList<T>() : newList;
    }

    public List<T> getNewList() {
        return newList;
    }

    public List<T> getOldList() {
        return oldList;
    }

    public void setOldList(@Nullable List<T> oldList) {
        this.oldList = oldList == null ? new ArrayList<T>() : oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return areItemsTheSame(oldList.get(oldItemPosition), newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return areContentsTheSame(oldList.get(oldItemPosition), newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return getChangePayload(oldList.get(oldItemPosition), newList.get(newItemPosition));
    }

    /**
     * @param oldItem New data
     * @param newItem old Data
     * @return Return false if items are no same
     */
    protected abstract boolean areItemsTheSame(@NonNull T oldItem, @NonNull T newItem);

    /**
     * @param oldItem New data
     * @param newItem old Data
     * @return Return false if item content are no same
     */
    protected abstract boolean areContentsTheSame(@NonNull T oldItem, @NonNull T newItem);

    /**
     * Optional implementation
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return Payload info
     */
    @Nullable
    protected Object getChangePayload(@NonNull T oldItem, @NonNull T newItem) {
        return null;
    }
}
