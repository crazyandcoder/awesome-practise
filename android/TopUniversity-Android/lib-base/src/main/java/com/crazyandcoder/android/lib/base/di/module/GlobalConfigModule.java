package com.crazyandcoder.android.lib.base.di.module;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.crazyandcoder.android.lib.base.http.BaseUrl;
import com.crazyandcoder.android.lib.base.http.GlobalHttpHandler;
import com.crazyandcoder.android.lib.base.http.log.DefaultFormatPrinter;
import com.crazyandcoder.android.lib.base.http.log.FormatPrinter;
import com.crazyandcoder.android.lib.base.http.log.RequestInterceptor;
import com.crazyandcoder.android.lib.base.integration.IRepositoryManager;
import com.crazyandcoder.android.lib.base.integration.cache.Cache;
import com.crazyandcoder.android.lib.base.integration.cache.CacheType;
import com.crazyandcoder.android.lib.base.integration.cache.IntelligentCache;
import com.crazyandcoder.android.lib.base.integration.cache.LruCache;
import com.crazyandcoder.android.lib.base.utils.DataHelper;
import com.crazyandcoder.android.lib.base.utils.Preconditions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.internal.Util;

/**
 * @ClassName: GlobalConfigModule
 * @Description: 框架独创的建造者模式 {@link Module},可向框架中注入外部配置的自定义参数
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/11 6:28 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/11 6:28 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Module
public class GlobalConfigModule {
    private HttpUrl mApiUrl;
    private BaseUrl mBaseUrl;
    private GlobalHttpHandler mHandler;
    private List<Interceptor> mInterceptors;
    private ResponseErrorListener mErrorListener;
    private File mCacheFile;
    private ClientModule.RetrofitConfiguration mRetrofitConfiguration;
    private ClientModule.OkhttpConfiguration mOkhttpConfiguration;
    private ClientModule.RxCacheConfiguration mRxCacheConfiguration;
    private AppModule.GsonConfiguration mGsonConfiguration;
    private RequestInterceptor.Level mPrintHttpLogLevel;
    private FormatPrinter mFormatPrinter;
    private Cache.Factory mCacheFactory;
    private ExecutorService mExecutorService;
    private IRepositoryManager.ObtainServiceDelegate mObtainServiceDelegate;

    private GlobalConfigModule(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mBaseUrl = builder.baseUrl;
        this.mHandler = builder.handler;
        this.mInterceptors = builder.interceptors;
        this.mErrorListener = builder.responseErrorListener;
        this.mCacheFile = builder.cacheFile;
        this.mRetrofitConfiguration = builder.retrofitConfiguration;
        this.mOkhttpConfiguration = builder.okhttpConfiguration;
        this.mRxCacheConfiguration = builder.rxCacheConfiguration;
        this.mGsonConfiguration = builder.gsonConfiguration;
        this.mPrintHttpLogLevel = builder.printHttpLogLevel;
        this.mFormatPrinter = builder.formatPrinter;
        this.mCacheFactory = builder.cacheFactory;
        this.mExecutorService = builder.executorService;
        this.mObtainServiceDelegate = builder.obtainServiceDelegate;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Singleton
    @Provides
    @Nullable
    List<Interceptor> provideInterceptors() {
        return mInterceptors;
    }

    /**
     * 提供 BaseUrl,默认使用 <"https://api.github.com/">
     *
     * @return
     */
    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        if (mBaseUrl != null) {
            HttpUrl httpUrl = mBaseUrl.url();
            if (httpUrl != null) {
                return httpUrl;
            }
        }
        return mApiUrl == null ? HttpUrl.parse("https://api.github.com/") : mApiUrl;
    }

    /**
     * 提供处理 Http 请求和响应结果的处理类
     *
     * @return
     */
    @Singleton
    @Provides
    @Nullable
    GlobalHttpHandler provideGlobalHttpHandler() {
        return mHandler;
    }

    /**
     * 提供缓存文件
     */
    @Singleton
    @Provides
    File provideCacheFile(Application application) {
        return mCacheFile == null ? DataHelper.getCacheFile(application) : mCacheFile;
    }

    /**
     * 提供处理 RxJava 错误的管理器的回调
     *
     * @return
     */
    @Singleton
    @Provides
    ResponseErrorListener provideResponseErrorListener() {
        return mErrorListener == null ? ResponseErrorListener.EMPTY : mErrorListener;
    }

    @Singleton
    @Provides
    @Nullable
    ClientModule.RetrofitConfiguration provideRetrofitConfiguration() {
        return mRetrofitConfiguration;
    }

    @Singleton
    @Provides
    @Nullable
    ClientModule.OkhttpConfiguration provideOkhttpConfiguration() {
        return mOkhttpConfiguration;
    }

    @Singleton
    @Provides
    @Nullable
    ClientModule.RxCacheConfiguration provideRxCacheConfiguration() {
        return mRxCacheConfiguration;
    }

    @Singleton
    @Provides
    @Nullable
    AppModule.GsonConfiguration provideGsonConfiguration() {
        return mGsonConfiguration;
    }

    @Singleton
    @Provides
    RequestInterceptor.Level providePrintHttpLogLevel() {
        return mPrintHttpLogLevel == null ? RequestInterceptor.Level.ALL : mPrintHttpLogLevel;
    }

    @Singleton
    @Provides
    FormatPrinter provideFormatPrinter() {
        return mFormatPrinter == null ? new DefaultFormatPrinter() : mFormatPrinter;
    }

    @Singleton
    @Provides
    Cache.Factory provideCacheFactory(Application application) {
        return mCacheFactory == null ? type -> {
            //若想自定义 LruCache 的 size, 或者不想使用 LruCache, 想使用自己自定义的策略
            //使用 GlobalConfigModule.Builder#cacheFactory() 即可扩展
            switch (type.getCacheTypeId()) {
                //Activity、Fragment 以及 Extras 使用 IntelligentCache (具有 LruCache 和 可永久存储数据的 Map)
                case CacheType.EXTRAS_TYPE_ID:
                case CacheType.ACTIVITY_CACHE_TYPE_ID:
                case CacheType.FRAGMENT_CACHE_TYPE_ID:
                    return new IntelligentCache(type.calculateCacheSize(application));
                //其余使用 LruCache (当达到最大容量时可根据 LRU 算法抛弃不合规数据)
                default:
                    return new LruCache(type.calculateCacheSize(application));
            }
        } : mCacheFactory;
    }

    /**
     * 返回一个全局公用的线程池,适用于大多数异步需求。
     * 避免多个线程池创建带来的资源消耗。
     *
     * @return {@link Executor}
     */
    @Singleton
    @Provides
    ExecutorService provideExecutorService() {
        return mExecutorService == null ? new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                new SynchronousQueue<>(), Util.threadFactory("Arms Executor", false)) : mExecutorService;
    }

    @Singleton
    @Provides
    @Nullable
    IRepositoryManager.ObtainServiceDelegate provideObtainServiceDelegate() {
        return mObtainServiceDelegate;
    }

    public static final class Builder {
        private HttpUrl apiUrl;
        private BaseUrl baseUrl;
        private GlobalHttpHandler handler;
        private List<Interceptor> interceptors;
        private ResponseErrorListener responseErrorListener;
        private File cacheFile;
        private ClientModule.RetrofitConfiguration retrofitConfiguration;
        private ClientModule.OkhttpConfiguration okhttpConfiguration;
        private ClientModule.RxCacheConfiguration rxCacheConfiguration;
        private AppModule.GsonConfiguration gsonConfiguration;
        private RequestInterceptor.Level printHttpLogLevel;
        private FormatPrinter formatPrinter;
        private Cache.Factory cacheFactory;
        private ExecutorService executorService;
        private IRepositoryManager.ObtainServiceDelegate obtainServiceDelegate;

        private Builder() {
        }

        public Builder baseurl(String baseUrl) {//基础url
            if (TextUtils.isEmpty(baseUrl)) {
                throw new NullPointerException("BaseUrl can not be empty");
            }
            this.apiUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        public Builder baseurl(BaseUrl baseUrl) {
            this.baseUrl = Preconditions.checkNotNull(baseUrl, BaseUrl.class.getCanonicalName() + "can not be null.");
            return this;
        }

        public Builder globalHttpHandler(GlobalHttpHandler handler) {//用来处理http响应结果
            this.handler = handler;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {//动态添加任意个interceptor
            if (interceptors == null) {
                interceptors = new ArrayList<>();
            }
            this.interceptors.add(interceptor);
            return this;
        }

        public Builder responseErrorListener(ResponseErrorListener listener) {//处理所有RxJava的onError逻辑
            this.responseErrorListener = listener;
            return this;
        }

        public Builder cacheFile(File cacheFile) {
            this.cacheFile = cacheFile;
            return this;
        }

        public Builder retrofitConfiguration(ClientModule.RetrofitConfiguration retrofitConfiguration) {
            this.retrofitConfiguration = retrofitConfiguration;
            return this;
        }

        public Builder okhttpConfiguration(ClientModule.OkhttpConfiguration okhttpConfiguration) {
            this.okhttpConfiguration = okhttpConfiguration;
            return this;
        }

        public Builder rxCacheConfiguration(ClientModule.RxCacheConfiguration rxCacheConfiguration) {
            this.rxCacheConfiguration = rxCacheConfiguration;
            return this;
        }

        public Builder gsonConfiguration(AppModule.GsonConfiguration gsonConfiguration) {
            this.gsonConfiguration = gsonConfiguration;
            return this;
        }

        public Builder printHttpLogLevel(RequestInterceptor.Level printHttpLogLevel) {//是否让框架打印 Http 的请求和响应信息
            this.printHttpLogLevel = Preconditions.checkNotNull(printHttpLogLevel, "The printHttpLogLevel can not be null, use RequestInterceptor.Level.NONE instead.");
            return this;
        }

        public Builder formatPrinter(FormatPrinter formatPrinter) {
            this.formatPrinter = Preconditions.checkNotNull(formatPrinter, FormatPrinter.class.getCanonicalName() + "can not be null.");
            return this;
        }

        public Builder cacheFactory(Cache.Factory cacheFactory) {
            this.cacheFactory = cacheFactory;
            return this;
        }

        public Builder executorService(ExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        public Builder obtainServiceDelegate(IRepositoryManager.ObtainServiceDelegate obtainServiceDelegate) {
            this.obtainServiceDelegate = obtainServiceDelegate;
            return this;
        }

        public GlobalConfigModule build() {
            return new GlobalConfigModule(this);
        }
    }
}

