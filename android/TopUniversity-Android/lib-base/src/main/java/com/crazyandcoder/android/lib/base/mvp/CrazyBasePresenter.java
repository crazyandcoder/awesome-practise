/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.crazyandcoder.android.lib.base.mvp;

import android.app.Activity;
import android.app.Service;
import android.view.View;

import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.crazyandcoder.android.lib.base.http.OnRequestCallback;
import com.crazyandcoder.android.lib.base.integration.EventBusManager;
import com.crazyandcoder.android.lib.base.utils.Preconditions;
import com.crazyandcoder.android.lib.base.utils.RxLifecycleUtils;
import com.trello.rxlifecycle2.RxLifecycle;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * ================================================
 * 基类 Presenter
 * ================================================
 */
public class CrazyBasePresenter<M extends ICrazyModel, V extends ICrazyView> implements ICrazyPresenter, LifecycleObserver {
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;
    protected M mModel;
    protected V mRootView;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param rootView
     */
    public CrazyBasePresenter(M model, V rootView) {
        Preconditions.checkNotNull(model, "%s cannot be null", ICrazyModel.class.getName());
        Preconditions.checkNotNull(rootView, "%s cannot be null", ICrazyView.class.getName());
        this.mModel = model;
        this.mRootView = rootView;
        onStart();
    }

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     *
     * @param rootView
     */
    public CrazyBasePresenter(V rootView) {
        Preconditions.checkNotNull(rootView, "%s cannot be null", ICrazyView.class.getName());
        this.mRootView = rootView;
        onStart();
    }

    public CrazyBasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {
        //将 LifecycleObserver 注册给 LifecycleOwner 后 @OnLifecycleEvent 才可以正常使用
        if (mRootView != null && mRootView instanceof LifecycleOwner) {
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
            if (mModel != null && mModel instanceof LifecycleObserver) {
                ((LifecycleOwner) mRootView).getLifecycle().addObserver((LifecycleObserver) mModel);
            }
        }
        if (useEventBus()) {
            EventBusManager.getInstance().register(this);
        }
    }

    /**
     * 在框架中 {@link Activity#onDestroy()} 时会默认调用 {@link ICrazyPresenter#onDestroy()}
     */
    @Override
    public void onDestroy() {
        if (useEventBus()) {
            EventBusManager.getInstance().unregister(this);
        }
        unDispose();//解除订阅
        if (mModel != null) {
            mModel.onDestroy();
        }
        this.mModel = null;
        this.mRootView = null;
        this.mCompositeDisposable = null;
    }

    /**
     * 只有当 {@code mRootView} 不为 null, 并且 {@code mRootView} 实现了 {@link LifecycleOwner} 时, 此方法才会被调用
     * 所以当您想在 {@link Service} 以及一些自定义 {@link View} 或自定义类中使用 {@code Presenter} 时
     * 您也将不能继续使用 {@link OnLifecycleEvent} 绑定生命周期
     *
     * @param owner link {@link ComponentActivity} and {@link Fragment}
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        /**
         * 注意, 如果在这里调用了 {@link #onDestroy()} 方法, 会出现某些地方引用 {@code mModel} 或 {@code mRootView} 为 null 的情况
         * 比如在 {@link RxLifecycle} 终止 {@link Observable} 时, 在 {@link io.reactivex.Observable#doFinally(Action)} 中却引用了 {@code mRootView} 做一些释放资源的操作, 此时会空指针
         * 或者如果你声明了多个 @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) 时在其他 @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
         * 中引用了 {@code mModel} 或 {@code mRootView} 也可能会出现此情况
         */
        owner.getLifecycle().removeObserver(this);
    }

    /**
     * 是否使用 EventBus
     * Arms 核心库现在并不会依赖某个 EventBus, 要想使用 EventBus, 还请在项目中自行依赖对应的 EventBus
     * 现在支持两种 EventBus, greenrobot 的 EventBus 和畅销书 《Android源码设计模式解析与实战》的作者 何红辉 所作的 AndroidEventBus
     * 确保依赖后, 将此方法返回 true, Arms 会自动检测您依赖的 EventBus, 并自动注册
     * 这种做法可以让使用者有自行选择三方库的权利, 并且还可以减轻 Arms 的体积
     *
     * @return 返回 {@code true} (默认为使用 {@code true}), Arms 会自动注册 EventBus
     */
    public boolean useEventBus() {
        return false;
    }

    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 目前框架已使用 {@link RxLifecycle} 避免内存泄漏,此方法作为备用方案
     *
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 用这个进行网络请求
     *
     * @param <T>
     * @param isShowLoading
     * @param observable
     * @param observer
     */
    protected <T> void httpRequestDetail(boolean isShowLoading, Observable observable, OnRequestCallback<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (isShowLoading) {
                        mRootView.showLoading();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (isShowLoading) {
                        mRootView.hideLoading();
                    }
                }).compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(observer);

    }

    /**
     * 用这个进行网络请求
     *
     * @param <T>
     * @param observable
     * @param observer
     */
    protected <T> void httpRequestDetail(Observable observable, OnRequestCallback<T> observer) {
        httpRequestDetail(true, observable, observer);

    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
