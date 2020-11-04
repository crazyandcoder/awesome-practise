package com.crazyandcoder.android.lib.base.http;


import android.app.Application;
import android.content.Context;
import android.net.ParseException;

import com.crazyandcoder.android.lib.base.bean.BaseResult;
import com.crazyandcoder.android.lib.base.integration.AppManager;
import com.crazyandcoder.android.lib.base.utils.CrazyUtils;
import com.crazyandcoder.android.lib.base.widget.toast.ToastUtils;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import retrofit2.HttpException;
import timber.log.Timber;

/**
 * @ClassName: OnRequestCallback
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/22 2:42 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/22 2:42 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class OnRequestCallback<T> extends ErrorHandleSubscriber<BaseResult<T>> {


    private Application application;

    public abstract void onSuccess(T t, BaseResult objResult);

    public void onFailure(int code, String resultMsg) {
        if (application != null) {
            ToastUtils.showToast(application, "" + resultMsg);
        }
    }

    @Override
    public void onError(Throwable t) {
        super.onError(t);
    }

    public OnRequestCallback(Application application, RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
        this.application = application;
    }


    @Override
    public void onNext(BaseResult<T> tBaseResult) {
        switch (tBaseResult.getCode()) {
            case 0:
                if (tBaseResult != null) {
                    onSuccess(tBaseResult.getData(), tBaseResult);
                } else {
                    onFailure(-503, "数据解析失败,BaseResult不可为null");
                }
                break;

            default:
                if (tBaseResult != null) {
                    onFailure(tBaseResult.getCode(), tBaseResult.getMsg());
                } else {
                    onFailure(-504, "数据解析失败，BaseResult不可为null");
                }
                break;
        }
    }

}