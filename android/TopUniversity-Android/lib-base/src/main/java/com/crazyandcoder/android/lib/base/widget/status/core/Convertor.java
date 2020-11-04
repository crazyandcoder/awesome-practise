package com.crazyandcoder.android.lib.base.widget.status.core;

import com.crazyandcoder.android.lib.base.widget.status.callback.Callback;

/**
 * @ClassName: Convertor
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/27 4:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/27 4:11 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface Convertor<T> {
    Class<?extends Callback> map(T t);
}
