package com.crazyandcoder.android.lib.base.utils;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {


    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'b', 'e', 'e', 'f'};

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * @param encryptStr
     * @Description:加密
     */
    public static String encrypt16(String encryptStr) {
        return MD5_32(encryptStr).substring(8, 24);
    }
//
//    @NonNull
//    public static String md5(String string) {
//        if (TextUtils.isEmpty(string)) {
//            return "";
//        }
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//            byte[] bytes = md5.digest(string.getBytes());
//            StringBuilder result = new StringBuilder();
//            for (byte b : bytes) {
//                String temp = Integer.toHexString(b & 0xff);
//                if (temp.length() == 1) {
//                    temp = "0" + temp;
//                }
//                result.append(temp);
//            }
//            return result.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }

    /***
     * MD5
     */
    public static String MD5_32(String inStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            //System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }


    @NonNull
    public static String md5(File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }
        FileInputStream in = null;
        String result = "";
        byte buffer[] = new byte[8192];
        int len;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            byte[] bytes = md5.digest();

            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    @NonNull
    public static String md5(String string, int times) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        String md5 = MD5_32(string);
        for (int i = 0; i < times - 1; i++) {
            md5 = MD5_32(md5);
        }
        return md5;

    }

    /**
     * 加盐就是使用一个额外的盐值与原字符串一起加密，通常盐值可以使用用户名、string明文的hascode或是随机生成的字符串。
     *
     * @param string
     * @param slat
     * @return
     */
    @NonNull
    public static String md5(String string, String slat) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string + slat).getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
