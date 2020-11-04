package com.crazyandcoder.android.lib.common.utils.log;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
/**
 * @ClassName: FileLog
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/27 11:08 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/27 11:08 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class FileLog {
    private static final String FILE_PREFIX = "KLog_";
    private static final String FILE_FORMAT = ".log";

    public static void printFile(String tag, File targetDirectory, @Nullable String fileName, String headString, String msg) {

        fileName = (fileName == null) ? getFileName() : fileName;
        if (save(targetDirectory, fileName, msg)) {
            Log.d(tag, headString + " save log success ! location is >>>" + targetDirectory.getAbsolutePath() + "/" + fileName);
        } else {
            Log.e(tag, headString + "save log fails !");
        }
    }

    private static boolean save(File dic, @NonNull String fileName, String msg) {

        File file = new File(dic, fileName);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(msg);
            outputStreamWriter.flush();
            outputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private static String getFileName() {
        Random random = new Random();
        return FILE_PREFIX + Long.toString(System.currentTimeMillis() + random.nextInt(10000)).substring(4) + FILE_FORMAT;
    }

}
