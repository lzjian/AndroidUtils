package com.lzjian.androidutils.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: String工具类
 */
public class StringUtils {

    private static final String EMAIL_CHECK = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static boolean isPhoneNum(String str) {
        if (TextUtils.isEmpty(str)) return false;
        Pattern pattern = Pattern.compile("[0-9]{11}");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isPassword(String str) {
        if (TextUtils.isEmpty(str)) return false;
        String regEx = "^\\w{6,12}$";
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(str).matches();
    }

    public static boolean isEmail(String str) {
        if (TextUtils.isEmpty(str)) return false;
        Pattern pattern = Pattern.compile(EMAIL_CHECK);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static List<String> stringToList(String str) {
        if (TextUtils.isEmpty(str)) return new ArrayList<>();
        String[] ss = str.split(";");
        return Arrays.asList(ss);
    }

    public static List<String> stringToList(String str, String regex) {
        if (TextUtils.isEmpty(str)) return new ArrayList<>();
        String[] ss = str.split(regex);
        return Arrays.asList(ss);
    }

    public static String listToString(List<String> list) {
        if (list == null || list.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(";");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static String listToString(List<String> list, String regex) {
        if (list == null || list.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(regex);
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    // 匹配字符串和关键字, 得到关键字第一次在字符串里出现的位置(包括关键字首字母和末尾字母在字符串的位置)
    public static Integer[] match(String longStr, String keyword){
        longStr = longStr.toLowerCase();
        keyword = keyword.toLowerCase();
        if (longStr.contains(keyword)){
            int position = longStr.indexOf(keyword);
            Integer[] ints = new Integer[2];
            ints[0] = position;
            ints[1] = position+keyword.length();
            return ints;
        }
        return null;
    }
}
