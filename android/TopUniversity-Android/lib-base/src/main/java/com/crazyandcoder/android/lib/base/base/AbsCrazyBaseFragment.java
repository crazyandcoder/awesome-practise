package com.crazyandcoder.android.lib.base.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crazyandcoder.android.lib.base.delegate.IFragment;
import com.crazyandcoder.android.lib.base.integration.cache.Cache;
import com.crazyandcoder.android.lib.base.integration.cache.CacheType;
import com.crazyandcoder.android.lib.base.integration.lifecycle.FragmentLifecycleable;
import com.crazyandcoder.android.lib.base.mvp.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.utils.CrazyUtils;
import com.trello.rxlifecycle2.android.FragmentEvent;

import javax.inject.Inject;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AbsCrazyBaseFragment<P extends ICrazyPresenter> extends Fragment implements IFragment, FragmentLifecycleable {

    protected final String TAG = this.getClass().getSimpleName();
    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();
    protected Context mContext;

    //如果当前页面逻辑简单, Presenter 可以为 null
    @Inject
    @Nullable
    protected P mPresenter;
    private Cache<String, Object> mCache;


    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            //noinspection unchecked
            mCache = CrazyUtils.obtainAppComponentFromContext(getActivity()).cacheFactory().build(CacheType.FRAGMENT_CACHE);
        }
        return mCache;
    }

    @NonNull
    @Override
    public final Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initLayout(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();//释放资源
        }
        this.mPresenter = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    /**
     * 是否使用 EventBus
     * Arms 核心库现在并不会依赖某个 EventBus, 要想使用 EventBus, 还请在项目中自行依赖对应的 EventBus
     * 现在支持两种 EventBus, greenrobot 的 EventBus 和畅销书 《Android源码设计模式解析与实战》的作者 何红辉 所作的 AndroidEventBus
     * 确保依赖后, 将此方法返回 true, Arms 会自动检测您依赖的 EventBus, 并自动注册
     * 这种做法可以让使用者有自行选择三方库的权利, 并且还可以减轻 Arms 的体积
     *
     * @return 返回 {@code true} (默认为 {@code true}), Arms 会自动注册 EventBus
     */
    @Override
    public boolean useEventBus() {
        return false;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(View headView, @IdRes int viewId) {
        View view = headView.findViewById(viewId);
        return (T) view;
    }

}
