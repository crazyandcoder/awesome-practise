package com.crazyandcoder.android.lib.base.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.crazyandcoder.android.lib.base.utils.downlaoder.SingleMediaScanner;
import com.crazyandcoder.android.lib.base.widget.toast.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: SDcardUtils
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/24 10:24 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/24 10:24 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SDcardUtils {

    /**
     * 保存到本地相册
     *
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)
                ? Environment.getExternalStorageDirectory().getAbsolutePath()
                : "/mnt/sdcard";//保存到SD卡

        // 首先保存图片
        File appDir = new File(SAVE_PIC_PATH + "/AXDailySalary/");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        long nowSystemTime = System.currentTimeMillis();
        String fileName = nowSystemTime + ".png";
        File file = new File(appDir, fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //保存图片后发送广播通知更新数据库
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);


        ToastUtils.showToast(context, "已保存到本地相册");
    }

    /**
     * 保存位图到本地
     *
     * @param bitmap
     * @param path   本地路径
     * @return void
     */
    public static void SavaImage(Context context, Bitmap bitmap, String path) {
        File file = new File(path);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
        FileOutputStream fileOutputStream = null;
        String filePhth;
        String fileName;
        //文件夹不存在，则创建它
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            filePhth = path + "/" + "员工邀请二维码" + ".png";
            fileName = System.currentTimeMillis() + "";
            File file1 = new File(filePhth);
            fileOutputStream = new FileOutputStream(file1.getPath());
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
            //图片路径
            MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "", "");
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file1.getAbsolutePath())));
            Log.d("AXDailySalary", file1.getAbsolutePath() + "-----" + path);
        } catch (Exception e) {
            ToastUtils.showToast(context, "保存失败");
            e.printStackTrace();
        }
    }

    public static void saveImage2Gallery(Context context, Bitmap bmp) {
        Log.d("ZoomImage", "saveImageToGallery:" + bmp);
        final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)
                ? Environment.getExternalStorageDirectory().getAbsolutePath()
                : "/mnt/sdcard";//保存到SD卡

        // 首先保存图片
        File appDir = new File(SAVE_PIC_PATH + "/ZoomImage/");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        long nowSystemTime = System.currentTimeMillis();
        String fileName = nowSystemTime + ".png";
        File file = new File(appDir, fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ToastUtils.showToast(context, "已保存到本地相册");
    }

    /*
     * 保存文件，文件名为当前日期
     */
    public static boolean saveBitmap(Context context, Bitmap bitmap, String bitName) {
        String fileName;
        File file;
        String brand = Build.BRAND;

        if (brand.equals("xiaomi")) {
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        } else if (brand.equalsIgnoreCase("Huawei")) {
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        } else { // Meizu 、Oppo
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            saveSignImage(context, bitName, bitmap);
            return true;
        } else {
            Log.v("saveBitmap brand", "" + brand);
            file = new File(fileName);
        }
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
// 格式为 JPEG，照相机拍出的图片为JPEG格式的，PNG格式的不能显示在相册中
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush();
                out.close();
// 插入图库
                if (Build.VERSION.SDK_INT >= 29) {
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                    Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                } else {
                    MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), bitName, null);

                }

            }
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException", "FileNotFoundException:" + e.getMessage().toString());
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            Log.e("IOException", "IOException:" + e.getMessage().toString());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            Log.e("IOException", "IOException:" + e.getMessage().toString());
            e.printStackTrace();
            return false;


        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + fileName)));

        return true;

    }


    //将文件保存到公共的媒体文件夹
//这里的filepath不是绝对路径，而是某个媒体文件夹下的子路径，和沙盒子文件夹类似
//这里的filename单纯的指文件名，不包含路径
    public static void saveSignImage(Context context, String fileName, Bitmap bitmap) {
        try {
            //设置保存参数到ContentValues中
            ContentValues contentValues = new ContentValues();
            //设置文件名
            contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
            //兼容Android Q和以下版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                //android Q中不再使用DATA字段，而用RELATIVE_PATH代替
                //RELATIVE_PATH是相对路径不是绝对路径
                //DCIM是系统文件夹，关于系统文件夹可以到系统自带的文件管理器中查看，不可以写没存在的名字
                contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/");
                //contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Music/signImage");
            } else {
                contentValues.put(MediaStore.Images.Media.DATA, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
            }
            //设置文件类型
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/JPEG");
            //执行insert操作，向系统文件夹中添加文件
            //EXTERNAL_CONTENT_URI代表外部存储器，该值不变
            Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (uri != null) {
                //若生成了uri，则表示该文件添加成功
                //使用流将内容写入该uri中即可
                OutputStream outputStream = context.getContentResolver().openOutputStream(uri);
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                    outputStream.flush();
                    outputStream.close();
                }
            }
        } catch (Exception e) {
        }
    }


}
