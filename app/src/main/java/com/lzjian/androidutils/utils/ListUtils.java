package com.lzjian.androidutils.utils;

import java.util.HashSet;
import java.util.List;

/**
 * @Description: List工具类
 */

public class ListUtils {

    // 删掉list里的重复元素
    public static List removeDuplicateItem(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
