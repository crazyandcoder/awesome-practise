package com.crazyandcoder.android.lib.common.utils.rxjava;

import com.crazyandcoder.android.lib.common.utils.log.CrazyLog;

import io.reactivex.functions.Consumer;

/**
 * @ClassName: SimpleThrowableAction
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 4:57 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 4:57 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public final class SimpleThrowableAction implements Consumer<Throwable> {

    private String mTag;

    public SimpleThrowableAction(String tag) {
        mTag = tag;
    }

    @Override
    public void accept(Throwable throwable) throws Exception {
        CrazyLog.d(mTag, "订阅发生错误！", throwable);
    }
}
