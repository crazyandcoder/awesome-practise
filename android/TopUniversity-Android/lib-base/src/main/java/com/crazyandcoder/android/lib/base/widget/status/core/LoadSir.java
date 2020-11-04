package com.crazyandcoder.android.lib.base.widget.status.core;

import androidx.annotation.NonNull;

import com.crazyandcoder.android.lib.base.widget.status.LoadSirUtil;
import com.crazyandcoder.android.lib.base.widget.status.callback.Callback;
import com.crazyandcoder.android.lib.base.widget.status.target.ActivityTarget;
import com.crazyandcoder.android.lib.base.widget.status.target.ITarget;
import com.crazyandcoder.android.lib.base.widget.status.target.ViewTarget;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LoadSir
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:13 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:13 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoadSir {
    private static volatile LoadSir loadSir;
    private Builder builder;

    public static LoadSir getDefault() {
        if (loadSir == null) {
            synchronized (LoadSir.class) {
                if (loadSir == null) {
                    loadSir = new LoadSir();
                }
            }
        }
        return loadSir;
    }

    private LoadSir() {
        this.builder = new Builder();
    }

    private void setBuilder(@NonNull Builder builder) {
        this.builder = builder;
    }

    private LoadSir(Builder builder) {
        this.builder = builder;
    }

    public LoadService register(@NonNull Object target) {
        return register(target, null, null);
    }

    public LoadService register(Object target, Callback.OnReloadListener onReloadListener) {
        return register(target, onReloadListener, null);
    }

    public <T> LoadService register(Object target, Callback.OnReloadListener onReloadListener, Convertor<T>
            convertor) {
        ITarget targetContext = LoadSirUtil.getTargetContext(target, builder.getTargetContextList());
        LoadLayout loadLayout = targetContext.replaceView(target, onReloadListener);
        return new LoadService<>(convertor,loadLayout,  builder);
    }

    public static Builder beginBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Callback> callbacks = new ArrayList<>();
        private List<ITarget> targetContextList = new ArrayList<>();
        private Class<? extends Callback> defaultCallback;

        {
            targetContextList.add(new ActivityTarget());
            targetContextList.add(new ViewTarget());
        }

        public Builder addCallback(@NonNull Callback callback) {
            callbacks.add(callback);
            return this;
        }

        /**
         * @param targetContext
         * @return Builder
         * @since 1.3.8
         */
        public Builder addTargetContext(ITarget targetContext) {
            targetContextList.add(targetContext);
            return this;
        }

        public List<ITarget> getTargetContextList() {
            return targetContextList;
        }

        public Builder setDefaultCallback(@NonNull Class<? extends Callback> defaultCallback) {
            this.defaultCallback = defaultCallback;
            return this;
        }

        List<Callback> getCallbacks() {
            return callbacks;
        }

        Class<? extends Callback> getDefaultCallback() {
            return defaultCallback;
        }

        public void commit() {
            getDefault().setBuilder(this);
        }

        public LoadSir build() {
            return new LoadSir(this);
        }

    }
}
