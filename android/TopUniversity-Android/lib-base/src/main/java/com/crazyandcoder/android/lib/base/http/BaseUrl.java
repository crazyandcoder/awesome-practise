package com.crazyandcoder.android.lib.base.http;

import androidx.annotation.NonNull;

import okhttp3.HttpUrl;

/**
 * @ClassName: BaseUrl
 * @Description: 针对于 BaseUrl 在 App 启动时不能确定,需要请求服务器接口动态获取的应用场景
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/11 6:30 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/11 6:30 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface BaseUrl {
    /**
     * 在调用 Retrofit API 接口之前,使用 Okhttp 或其他方式,请求到正确的 BaseUrl 并通过此方法返回
     *
     * @return
     */
    @NonNull
    HttpUrl url();
}
