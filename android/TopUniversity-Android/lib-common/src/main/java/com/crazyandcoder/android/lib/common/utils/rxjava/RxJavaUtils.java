package com.crazyandcoder.android.lib.common.utils.rxjava;

/**
 * @ClassName: RxJavaUtils
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:52 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:52 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * RxJava工具
 *
 * @author xuexiang
 * @date 2018/3/4 上午1:20
 */
public final class RxJavaUtils {

    private final static String TAG = "RxJavaUtils";



    //========================延迟操作==========================//

    /**
     * 延迟操作
     *
     * @param delayTime 延迟时间
     * @param consumer  监听事件
     */
    public static Disposable delay(long delayTime, @NonNull Consumer<Long> consumer) {
        return delay(delayTime, TimeUnit.SECONDS, consumer, new SimpleThrowableAction(TAG));
    }

    /**
     * @param delayTime
     * @param consumer
     * @return
     */
    public static Disposable delay(float delayTime, @NonNull Consumer<Long> consumer) {
        return delay((long) (delayTime * 1000), TimeUnit.MILLISECONDS, consumer, new
                SimpleThrowableAction(TAG));
    }


    /**
     * 延迟操作
     *
     * @param delayTime     延迟时间
     * @param unit          延迟时间单位
     * @param consumer      监听事件
     * @param errorConsumer 出错的事件
     */
    public static Disposable delay(long delayTime, TimeUnit unit, @NonNull Consumer<Long>
            consumer, @NonNull Consumer<Throwable> errorConsumer) {
        return Flowable.timer(delayTime, unit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, errorConsumer);
    }


}

