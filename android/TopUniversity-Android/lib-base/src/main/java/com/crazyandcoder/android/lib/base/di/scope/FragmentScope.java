package com.crazyandcoder.android.lib.base.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @ClassName: ActivityScope
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/11 6:29 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/11 6:29 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}

