package com.crazyandcoder.top.university.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.crazyandcoder.android.lib.base.delegate.AppLifecycles;
import com.crazyandcoder.android.lib.base.di.module.ClientModule;
import com.crazyandcoder.android.lib.base.di.module.GlobalConfigModule;
import com.crazyandcoder.android.lib.base.http.log.RequestInterceptor;
import com.crazyandcoder.android.lib.base.integration.ConfigModule;
import com.crazyandcoder.top.university.BuildConfig;
import com.crazyandcoder.top.university.api.ApiDomain;

import java.util.List;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;

public class AppConfiguration implements ConfigModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlobalConfigModule.Builder builder) {
        if (!BuildConfig.LOG_DEBUG) {
            builder.printHttpLogLevel(RequestInterceptor.Level.NONE);
        }

        builder.baseurl(ApiDomain.APP_DOMAIN)
                .globalHttpHandler(new GlobalHttpHandlerImpl(context))
                .responseErrorListener(new ResponseErrorListenerImpl())
                .gsonConfiguration((context1, gsonBuilder) -> {
                    gsonBuilder
                            .serializeNulls()
                            //支持序列化null的参数
                            .enableComplexMapKeySerialization();
                })
                .okhttpConfiguration(new ClientModule.OkhttpConfiguration() {
                    @Override
                    public void configOkhttp(Context context, OkHttpClient.Builder builder) {
                        builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getTrustManager());
                        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());
                        //让 Retrofit 同时支持多个 BaseUrl 以及动态改变 BaseUrl.
                        RetrofitUrlManager.getInstance().with(builder);
                    }
                })
                .rxCacheConfiguration((context1, rxCacheBuilder) -> {
                    rxCacheBuilder.useExpiredDataIfLoaderNotAvailable(true);
                    return null;
                });
    }

    @Override
    public void injectAppLifecycle(@NonNull Context context, @NonNull List<AppLifecycles> lifecycles) {
        // AppDelegate.Lifecycle 的所有方法都会在基类Application对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        lifecycles.add(new ApplicationLifecycleCallbacksImpl());
    }

    @Override
    public void injectActivityLifecycle(@NonNull Context context, @NonNull List<Application.ActivityLifecycleCallbacks> lifecycles) {
        lifecycles.add(new ActivityLifecycleCallbacksImpl());
    }

    @Override
    public void injectFragmentLifecycle(@NonNull Context context, @NonNull List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        lifecycles.add(new FragmentLifecycleCallbacksImpl());
    }
}
