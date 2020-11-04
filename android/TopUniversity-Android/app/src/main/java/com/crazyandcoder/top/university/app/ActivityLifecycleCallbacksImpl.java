/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.crazyandcoder.top.university.app;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.crazyandcoder.android.lib.base.utils.CrazyUtils;


public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (!activity.getIntent().getBooleanExtra("isInitToolbar", false)) {
            //由于加强框架的兼容性,故将 setContentView 放到 onActivityCreated 之后,onActivityStarted 之前执行
            //而 findViewById 必须在 Activity setContentView() 后才有效,所以将以下代码从之前的 onActivityCreated 中移动到 onActivityStarted 中执行
            activity.getIntent().putExtra("isInitToolbar", true);
            //这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
            if (CrazyUtils.findViewByName(activity.getApplicationContext(), activity, "public_toolbar") != null) {
                if (activity instanceof AppCompatActivity) {
                    ((AppCompatActivity) activity).setSupportActionBar(CrazyUtils.findViewByName(activity.getApplicationContext(), activity, "public_toolbar"));
                    ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        activity.setActionBar(CrazyUtils.findViewByName(activity.getApplicationContext(), activity, "public_toolbar"));
                        activity.getActionBar().setDisplayShowTitleEnabled(false);
                    }
                }
            }
            if (CrazyUtils.findViewByName(activity.getApplicationContext(), activity, "public_toolbar_title") != null) {
                ((TextView) CrazyUtils.findViewByName(activity.getApplicationContext(), activity, "public_toolbar_title")).setText(activity.getTitle());
            }
            if (CrazyUtils.findViewByName(activity.getApplicationContext(), activity, "public_toolbar_back") != null) {
                CrazyUtils.findViewByName(activity.getApplicationContext(), activity, "public_toolbar_back").setOnClickListener(v -> {
                    activity.onBackPressed();
                });
            }
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        //横竖屏切换或配置改变时, Activity 会被重新创建实例, 但 Bundle 中的基础数据会被保存下来,移除该数据是为了保证重新创建的实例可以正常工作
        activity.getIntent().removeExtra("isInitToolbar");
    }
}
