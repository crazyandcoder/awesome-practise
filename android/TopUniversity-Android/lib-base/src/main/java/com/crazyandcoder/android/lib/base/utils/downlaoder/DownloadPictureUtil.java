package com.crazyandcoder.android.lib.base.utils.downlaoder;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.crazyandcoder.android.lib.base.widget.toast.ToastUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.SimpleTimeZone;

/**
 * @ClassName: DownloadPictureUtil
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/26 12:21 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/26 12:21 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DownloadPictureUtil {
    public static void downloadPicture(final Context context, final String url, final String folderName) {
        Glide.with(context).downloadOnly().load(url).into(new FileTarget() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                ToastUtils.showToast(context, "开始下载...");
                super.onLoadStarted(placeholder);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                ToastUtils.showToast(context, "保存失败");
            }

            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                super.onResourceReady(resource, transition);

                // 传入的保存文件夹名
                final String downloadFolderName = folderName;
                // 保存的图片名称
                String name = "";
                try {
                    name = url.substring(url.lastIndexOf("/") + 1);
                    if (name.contains(".")) {
                        name = name.substring(0, name.lastIndexOf("."));
                    }
                    name = MD5Util.md5Encode(name);
                } catch (Exception e) {
                    e.printStackTrace();
                    name = System.currentTimeMillis() + "";
                }
                String mimeType = ImageUtil.getImageTypeWithMime(resource.getAbsolutePath());
                name = name + "." + mimeType;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // 大于等于29版本的保存方法
                    ContentResolver resolver = context.getContentResolver();
                    // 设置文件参数到ContentValues中
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.DISPLAY_NAME, name);
                    values.put(MediaStore.Images.Media.DESCRIPTION, name);
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/" + mimeType);
                    values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + downloadFolderName + "/");

                    Uri insertUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    BufferedInputStream inputStream = null;
                    OutputStream os = null;
                    try {
                        inputStream = new BufferedInputStream(new FileInputStream(resource.getAbsolutePath()));
                        if (insertUri != null) {
                            os = resolver.openOutputStream(insertUri);
                        }
                        if (os != null) {
                            byte[] buffer = new byte[1024 * 4];
                            int len;
                            while ((len = inputStream.read(buffer)) != -1) {
                                os.write(buffer, 0, len);
                            }
                            os.flush();
                        }
                        ToastUtils.showToast(context, "成功保存到 ".concat(Environment.DIRECTORY_PICTURES + "/" + downloadFolderName));
                    } catch (IOException e) {
                        e.printStackTrace();
                        ToastUtils.showToast(context, "保存失败");
                    } finally {
                        try {
                            if (os != null) {
                                os.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    // 低于29版本的保存方法
                    final String path = Environment.getExternalStorageDirectory() + "/" + downloadFolderName + "/";

                    FileUtil.createFileByDeleteOldFile(path + name);
                    boolean result = FileUtil.copyFile(resource, path, name);
                    if (result) {
                        ToastUtils.showToast(context, "成功保存到 ".concat(path));
                        new SingleMediaScanner(context, path.concat(name), new SingleMediaScanner.ScanListener() {
                            @Override
                            public void onScanFinish() {
                                // scanning...
                            }
                        });
                    } else {
                        ToastUtils.showToast(context, "保存失败");
                    }
                }
            }
        });
    }

    public static void downloadPicture(final Context context, final Bitmap bitmap, final String folderName) {
        Glide.with(context).load(bitmap).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                // 传入的保存文件夹名
                final String downloadFolderName = folderName;
                // 保存的图片名称
                String name = "";
                name = System.currentTimeMillis() + "";
                String mimeType = ImageUtil.getImageTypeWithMime(resource.getAbsolutePath());
                name = name + "." + mimeType;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // 大于等于29版本的保存方法
                    ContentResolver resolver = context.getContentResolver();
                    // 设置文件参数到ContentValues中
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.DISPLAY_NAME, name);
                    values.put(MediaStore.Images.Media.DESCRIPTION, name);
                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/" + mimeType);
                    values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + downloadFolderName + "/");

                    Uri insertUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    BufferedInputStream inputStream = null;
                    OutputStream os = null;
                    try {
                        inputStream = new BufferedInputStream(new FileInputStream(resource.getAbsolutePath()));
                        if (insertUri != null) {
                            os = resolver.openOutputStream(insertUri);
                        }
                        if (os != null) {
                            byte[] buffer = new byte[1024 * 4];
                            int len;
                            while ((len = inputStream.read(buffer)) != -1) {
                                os.write(buffer, 0, len);
                            }
                            os.flush();
                        }
                        ToastUtils.showToast(context, "成功保存到 ".concat(Environment.DIRECTORY_PICTURES + "/" + downloadFolderName));
                    } catch (IOException e) {
                        e.printStackTrace();
                        ToastUtils.showToast(context, "保存失败");
                    } finally {
                        try {
                            if (os != null) {
                                os.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    // 低于29版本的保存方法
                    final String path = Environment.getExternalStorageDirectory() + "/" + downloadFolderName + "/";

                    FileUtil.createFileByDeleteOldFile(path + name);
                    boolean result = FileUtil.copyFile(resource, path, name);
                    if (result) {
                        ToastUtils.showToast(context, "成功保存到 ".concat(path));
                        new SingleMediaScanner(context, path.concat(name), new SingleMediaScanner.ScanListener() {
                            @Override
                            public void onScanFinish() {
                                // scanning...
                            }
                        });
                    } else {
                        ToastUtils.showToast(context, "保存失败");
                    }
                }
            }
        });
    }

}
