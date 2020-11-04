package com.crazyandcoder.android.lib.base.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.View;

import com.crazyandcoder.android.lib.base.R;
import com.crazyandcoder.android.lib.base.delegate.IActivity;
import com.crazyandcoder.android.lib.base.integration.cache.Cache;
import com.crazyandcoder.android.lib.base.integration.cache.CacheType;
import com.crazyandcoder.android.lib.base.integration.lifecycle.ActivityLifecycleable;
import com.crazyandcoder.android.lib.base.mvp.ICrazyPresenter;
import com.crazyandcoder.android.lib.base.utils.CrazyUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle2.android.ActivityEvent;

import javax.inject.Inject;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

import static com.crazyandcoder.android.lib.base.utils.ThirdViewUtil.convertAutoView;

/**
 * @author admin
 */
public abstract class AbsCrazyBaseActivity<P extends ICrazyPresenter> extends AppCompatActivity implements IActivity, ActivityLifecycleable {

    protected final String TAG = this.getClass().getSimpleName();
    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    @Inject
    @Nullable
    protected P mPresenter;

    private Cache<String, Object> mCache;
    private Unbinder mUnbinder;


    @NonNull
    @Override
    public synchronized Cache<String, Object> provideCache() {
        if (mCache == null) {
            //noinspection unchecked
            mCache = CrazyUtils.obtainAppComponentFromContext(this).cacheFactory().build(CacheType.ACTIVITY_CACHE);
        }
        return mCache;
    }

    @NonNull
    @Override
    public final Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = convertAutoView(name, context, attrs);
        return view == null ? super.onCreateView(name, context, attrs) : view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            int layoutResID = initLayout(savedInstanceState);
            //如果initView返回0,框架则不会调用setContentView(),当然也不会 Bind ButterKnife
            if (layoutResID != 0) {
                setContentView(layoutResID);
                //绑定到butterknife
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            if (e instanceof InflateException) {
                throw e;
            }
            e.printStackTrace();
        }
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
        this.mUnbinder = null;
        if (mPresenter != null) {
            mPresenter.onDestroy();//释放资源
        }
        this.mPresenter = null;
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

    /**
     * 这个 {@link Activity} 是否会使用 {@link Fragment}, 框架会根据这个属性判断是否注册 {@link FragmentManager.FragmentLifecycleCallbacks}
     * 如果返回 {@code false}, 那意味着这个 {@link Activity} 不需要绑定 {@link Fragment}, 那你再在这个 {@link Activity} 中绑定继承于 {@link BaseFragment} 的 {@link Fragment} 将不起任何作用
     *
     * @return 返回 {@code true} (默认为 {@code true}), 则需要使用 {@link Fragment}
     */
    @Override
    public boolean useFragment() {
        return true;
    }
}
