package com.crazyandcoder.android.lib.base.utils;

/**
 * @ClassName: UrlEncoderUtils
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/11 6:35 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/11 6:35 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class UrlEncoderUtils {

    private UrlEncoderUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 判断 str 是否已经 URLEncoder.encode() 过
     * 经常遇到这样的情况, 拿到一个 URL, 但是搞不清楚到底要不要 URLEncoder.encode()
     * 不做 URLEncoder.encode() 吧, 担心出错, 做 URLEncoder.encode() 吧, 又怕重复了
     *
     * @param str 需要判断的内容
     * @return 返回 {@code true} 为被 URLEncoder.encode() 过
     */
    public static boolean hasUrlEncoded(String str) {
        boolean encode = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '%' && (i + 2) < str.length()) {
                // 判断是否符合urlEncode规范
                char c1 = str.charAt(i + 1);
                char c2 = str.charAt(i + 2);
                if (isValidHexChar(c1) && isValidHexChar(c2)) {
                    encode = true;
                    break;
                } else {
                    break;
                }
            }
        }
        return encode;
    }

    /**
     * 判断 c 是否是 16 进制的字符
     *
     * @param c 需要判断的字符
     * @return 返回 {@code true} 为 16 进制的字符
     */
    private static boolean isValidHexChar(char c) {
        return ('0' <= c && c <= '9') || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F');
    }
}
