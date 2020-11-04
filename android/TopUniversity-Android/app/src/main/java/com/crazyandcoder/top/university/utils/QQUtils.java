package com.crazyandcoder.top.university.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class QQUtils {


    /****************
     *
     * 发起添加群流程。群号：开源-从0到1(1163880751) 的 key 为： 5gM2Bs8kyZYpk8RnaGOOCcnXnofNf46N
     * 调用 joinQQGroup(5gM2Bs8kyZYpk8RnaGOOCcnXnofNf46N) 即可发起手Q客户端申请加群 开源-从0到1(1163880751)
     *
     * @return 返回true表示呼起手Q成功，返回false表示呼起失败
     ******************/
    public static boolean joinQQGroup(Context context) {
        String key = "5gM2Bs8kyZYpk8RnaGOOCcnXnofNf46N";
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26jump_from%3Dwebapi%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }
}
