package com.crazyandcoder.android.lib.common.utils.log;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: JsonLog
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 11:07 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 11:07 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JsonLog {
    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(CrazyLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(CrazyLog.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        CrazyLogUtil.printLine(tag, true);
        message = headString + CrazyLog.LINE_SEPARATOR + message;
        String[] lines = message.split(CrazyLog.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        CrazyLogUtil.printLine(tag, false);
    }
}
