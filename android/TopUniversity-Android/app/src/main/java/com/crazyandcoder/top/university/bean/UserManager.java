package com.crazyandcoder.top.university.bean;

import android.content.Context;

import com.crazyandcoder.android.lib.base.utils.CrazyUtils;
import com.crazyandcoder.top.university.utils.SharePreference;
import com.crazyandcoder.top.university.utils.SharePreferenceUtil;

public class UserManager {

    private static UserManager instance;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (CrazyUtils.isEmpty(instance)) {
            synchronized (UserManager.class) {
                if (CrazyUtils.isEmpty(instance)) {
                    instance = new UserManager();
                }
            }
        }

        return instance;
    }

    public void setUserId(String userId, String name) {
        SharePreferenceUtil.getInstance().setValue(SharePreference.USERID, userId);
        SharePreferenceUtil.getInstance().setValue(SharePreference.LOGINNAME, name);
    }

    public String getUserId() {
        return SharePreferenceUtil.getInstance().getValue(SharePreference.USERID, "");
    }

    public String getUserName() {
        return SharePreferenceUtil.getInstance().getValue(SharePreference.LOGINNAME, "");
    }

    /**
     * 退出登录
     */
    public void logOut(Context context) {
        SharePreferenceUtil.getInstance().setValue(SharePreference.USERID, "");
        SharePreferenceUtil.getInstance().setValue(SharePreference.LOGINNAME, "");
    }

    /**
     * 是否已经登录
     *
     * @return
     */
    public boolean login() {
        return !getUserName().equals("");
    }
}
