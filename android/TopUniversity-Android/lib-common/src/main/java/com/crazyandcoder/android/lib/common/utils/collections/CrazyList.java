package com.crazyandcoder.android.lib.common.utils.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: CrazyList
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/5/28 3:23 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/28 3:23 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CrazyList {

    /**
     * 按指定大小，分隔集合，将集合按规定个数分为n个部分
     *
     * @param <T>
     * @param list
     * @param len
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int len) {

        if (list == null || list.isEmpty() || len < 1) {
            return Collections.emptyList();
        }

        List<List<T>> result = new ArrayList<>();

        int size = list.size();
        int count = (size + len - 1) / len;

        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }

        return result;
    }


}
