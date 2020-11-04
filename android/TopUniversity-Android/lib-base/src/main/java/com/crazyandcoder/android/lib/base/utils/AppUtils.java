package com.crazyandcoder.android.lib.base.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.crazyandcoder.android.lib.base.base.BaseApplication;

public class AppUtils {

    // 机型名称
    private static final String DriveName = android.os.Build.MODEL;
    // 软件版本号: 1.1 版本 + build 0200

    private static String getAppname() {
        return BaseApplication.getAppContext().getPackageName();
    }

    public static String getVersionName() {
        try {
            PackageManager packageManager = BaseApplication.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(BaseApplication.getAppContext().getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    public static int getVersionCode() {
        try {
            PackageManager packageManager = BaseApplication.getAppContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(BaseApplication.getAppContext().getPackageName(),
                    0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static String getModel() {
        return DriveName;
    }

    public static String getPlatformVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 包名
     *
     * @return
     */
    public static String getPackageName() {
        return BaseApplication.getAppContext().getPackageName();
    }


}
