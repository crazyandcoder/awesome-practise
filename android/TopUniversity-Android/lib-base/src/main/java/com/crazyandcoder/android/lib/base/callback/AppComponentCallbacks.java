package com.crazyandcoder.android.lib.base.callback;

/**
 * @ClassName: AppComponentCallbacks
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/16 6:43 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/16 6:43 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.ComponentCallbacks2;
import android.content.ContentProvider;
import android.content.res.Configuration;

import androidx.fragment.app.Fragment;

import com.crazyandcoder.android.lib.base.di.component.AppComponent;

/**
 * {@link ComponentCallbacks2} 是一个细粒度的内存回收管理回调
 * {@link Application}、{@link Activity}、{@link Service}、{@link ContentProvider}、{@link Fragment} 实现了 {@link ComponentCallbacks2} 接口
 * 开发者应该实现 {@link ComponentCallbacks2#onTrimMemory(int)} 方法, 细粒度 release 内存, 参数的值不同可以体现出不同程度的内存可用情况
 * 响应 {@link ComponentCallbacks2#onTrimMemory(int)} 回调, 开发者的 App 会存活的更持久, 有利于用户体验
 * 不响应 {@link ComponentCallbacks2#onTrimMemory(int)} 回调, 系统 kill 掉进程的几率更大
 */
public class AppComponentCallbacks implements ComponentCallbacks2 {

    public AppComponentCallbacks(Application application, AppComponent appComponent) {

    }

    /**
     * 在你的 App 生命周期的任何阶段, {@link ComponentCallbacks2#onTrimMemory(int)} 发生的回调都预示着你设备的内存资源已经开始紧张
     * 你应该根据 {@link ComponentCallbacks2#onTrimMemory(int)} 发生回调时的内存级别来进一步决定释放哪些资源
     * {@link ComponentCallbacks2#onTrimMemory(int)} 的回调可以发生在 {@link Application}、{@link Activity}、{@link Service}、{@link ContentProvider}、{@link Fragment}
     *
     * @param level 内存级别
     * @see <a href="https://developer.android.com/reference/android/content/ComponentCallbacks2.html#TRIM_MEMORY_RUNNING_MODERATE">level 官方文档</a>
     */
    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    /**
     * 当系统开始清除 LRU 列表中的进程时, 尽管它会首先按照 LRU 的顺序来清除, 但是它同样会考虑进程的内存使用量, 因此消耗越少的进程则越容易被留下来
     * {@link ComponentCallbacks2#onTrimMemory(int)} 的回调是在 API 14 才被加进来的, 对于老的版本, 你可以使用 {@link ComponentCallbacks2#onLowMemory} 方法来进行兼容
     * {@link ComponentCallbacks2#onLowMemory} 相当于 {@code onTrimMemory(TRIM_MEMORY_COMPLETE)}
     *
     * @see #TRIM_MEMORY_COMPLETE
     */
    @Override
    public void onLowMemory() {
    }
}