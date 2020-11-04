package com.crazyandcoder.android.lib.base.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @ClassName: AbsCrazyBaseAdapter
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/12 4:14 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/12 4:14 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class AbsCrazyBaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<AbsCrazyBaseRecyclerViewHolder<T>> {
    protected List<T> mInfos;
    protected OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public AbsCrazyBaseRecyclerViewAdapter(List<T> infos) {
        super();
        this.mInfos = infos;
    }

    /**
     * 遍历所有 {@link AbsCrazyBaseRecyclerViewHolder}, 释放他们需要释放的资源
     *
     * @param recyclerView {@link RecyclerView}
     */
    public static void releaseAllHolder(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return;
        }
        for (int i = recyclerView.getChildCount() - 1; i >= 0; i--) {
            final View view = recyclerView.getChildAt(i);
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder instanceof AbsCrazyBaseRecyclerViewHolder) {
                ((AbsCrazyBaseRecyclerViewHolder) viewHolder).onRelease();
            }
        }
    }

    /**
     * 创建 {@link AbsCrazyBaseRecyclerViewHolder}
     *
     * @param parent   父容器
     * @param viewType 布局类型
     * @return {@link AbsCrazyBaseRecyclerViewHolder}
     */
    @NotNull
    @Override
    public AbsCrazyBaseRecyclerViewHolder<T> onCreateViewHolder(ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false);
        AbsCrazyBaseRecyclerViewHolder<T> mHolder = getHolder(view, viewType);
        //设置Item点击事件
        mHolder.setOnItemClickListener((view1, position) -> {
            if (mOnItemClickListener != null && mInfos.size() > 0) {
                //noinspection unchecked
                mOnItemClickListener.onItemClick(view1, viewType, mInfos.get(position), position);
            }
        });
        return mHolder;
    }

    /**
     * 绑定数据
     *
     * @param holder   {@link AbsCrazyBaseRecyclerViewHolder}
     * @param position 在 RecyclerView 中的位置
     */
    @Override
    public void onBindViewHolder(AbsCrazyBaseRecyclerViewHolder<T> holder, int position) {
        holder.setData(mInfos.get(position), position);
    }

    /**
     * 返回数据总个数
     *
     * @return 数据总个数
     */
    @Override
    public int getItemCount() {
        return mInfos.size();
    }

    /**
     * 返回数据集合
     *
     * @return 数据集合
     */
    public List<T> getInfos() {
        return mInfos;
    }

    /**
     * 获得 RecyclerView 中某个 position 上的 item 数据
     *
     * @param position 在 RecyclerView 中的位置
     * @return 数据
     */
    public T getItem(int position) {
        return mInfos == null ? null : mInfos.get(position);
    }

    /**
     * 让子类实现用以提供 {@link AbsCrazyBaseRecyclerViewHolder}
     *
     * @param v        用于展示的 {@link View}
     * @param viewType 布局类型
     * @return {@link AbsCrazyBaseRecyclerViewHolder}
     */
    @NonNull
    public abstract AbsCrazyBaseRecyclerViewHolder<T> getHolder(@NonNull View v, int viewType);

    /**
     * 提供用于 item 布局的 {@code layoutId}
     *
     * @param viewType 布局类型
     * @return 布局 id
     */
    public abstract int getLayoutId(int viewType);

    /**
     * 设置 item 点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /**
     * item 点击事件
     *
     * @param <T>
     */
    public interface OnRecyclerViewItemClickListener<T> {

        /**
         * item 被点击
         *
         * @param view     被点击的 {@link View}
         * @param viewType 布局类型
         * @param data     数据
         * @param position 在 RecyclerView 中的位置
         */
        void onItemClick(@NonNull View view, int viewType, @NonNull T data, int position);
    }

    public void setList(List<T> infos) {
        this.mInfos = infos;
        notifyDataSetChanged();
    }
}
