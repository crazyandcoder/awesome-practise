package com.crazyandcoder.android.lib.base.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crazyandcoder.android.lib.base.utils.ThirdViewUtil;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * @ClassName: AbsCrazyBaseRecyclerViewHolder
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/12 4:14 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/12 4:14 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class AbsCrazyBaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected final String TAG = this.getClass().getSimpleName();
    protected OnViewClickListener mOnViewClickListener = null;

    public AbsCrazyBaseRecyclerViewHolder(View itemView) {
        super(itemView);
        //点击事件
        itemView.setOnClickListener(this);

        //绑定 ButterKnife
        ThirdViewUtil.bindTarget(this, itemView);
    }

    /**
     * 设置数据
     *
     * @param data     数据
     * @param position 在 RecyclerView 中的位置
     */
    public abstract void setData(@NonNull T data, int position);

    /**
     * 在 Activity 的 onDestroy 中使用 {@link DefaultAdapter#releaseAllHolder(RecyclerView)} 方法 (super.onDestroy() 之前)
     * {@link AbsCrazyBaseRecyclerViewHolder#onRelease()} 才会被调用, 可以在此方法中释放一些资源
     */
    protected void onRelease() {

    }

    @Override
    public void onClick(View view) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener.onViewClick(view, this.getPosition());
        }
    }

    /**
     * item的点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }

    /**
     * item 点击事件
     */
    public interface OnViewClickListener {

        /**
         * item 被点击
         *
         * @param view     被点击的 {@link View}
         * @param position 在 RecyclerView 中的位置
         */
        void onViewClick(View view, int position);
    }
}