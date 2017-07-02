package com.lzjian.androidutils.utils;

import java.util.Date;

/**
 * @Description: 时间工具类
 */
public class TimeUtils {
    /**
     * @Description: 日期转中文显示
     */
    public static String getRecentTime(Date time) {
        Date now = new Date(); // 当前时间

        if (now.getYear() > time.getYear()) {
            return now.getYear() - time.getYear() + "年前";
        } else if (now.getYear() == time.getYear()) {
            if (now.getMonth() > time.getMonth()) {
                return now.getMonth() - time.getMonth() + "月前";
            } else if (now.getMonth() == time.getMonth()) {
                if (now.getDate() > time.getDate()) {
                    return now.getDate() - time.getDate() + "天前";
                } else if (now.getDate() == time.getDate()) {
                    if (now.getHours() > time.getHours()) {
                        return now.getHours() - time.getHours() + "小时前";
                    } else if (now.getHours() == time.getHours()) {
                        if (now.getMinutes() > time.getMinutes()) {
                            return now.getMinutes() - time.getMinutes() + "分钟前";
                        } else if (now.getMinutes() == time.getMinutes()) {
                            return "1分钟前";
                        } else {
                            return DateUtils.DateToString(time,
                                    DateUtils.DateStyle.HH_MM);
                        }
                    } else {
                        return DateUtils.DateToString(time,
                                DateUtils.DateStyle.HH_MM);
                    }
                } else {
                    return DateUtils.DateToString(time,
                            DateUtils.DateStyle.MM_DD);
                }
            } else {
                return DateUtils.DateToString(time, DateUtils.DateStyle.MM_DD);
            }
        } else {
            return DateUtils.DateToString(time, DateUtils.DateStyle.YYYY_MM_DD);
        }
    }

    /**
     * @Description: 日期转中文显示
     */
    public static String getRecentTime(String date) {
        return getRecentTime(DateUtils.StringToDate(date));
    }

    /**
     * @Description: 获取显示的时间
     */
    public static String getShowTime(String date) {
        return getShowTime(DateUtils.StringToDate(date));
    }

    /**
     * @Description: 获取显示的时间
     */
    public static String getShowTime(Date time) {
        Date now = new Date(); // 当前时间

        if (now.getYear() == time.getYear()
                && now.getMonth() == time.getMonth()) {
            if (now.getDate() == time.getDate()) {
                return DateUtils.DateToString(time, DateUtils.DateStyle.HH_MM);
            } else if (now.getDate() - time.getDate() < 8) {
                if (now.getDate() - time.getDate() == 1) {
                    return "昨天";
                }
                return DateUtils.getWeek(time).getChineseName();
            }
        }
        return DateUtils.DateToString(time, DateUtils.DateStyle.YYYY_MM_DD);
    }

    /**
     * 聊天时间的显示
     */
    public static String getChatTime(Date time) {
        Date now = new Date(); // 当前时间

        if (now.getYear() == time.getYear()
                && now.getMonth() == time.getMonth()) {
            if (now.getDate() == time.getDate()) {
                return DateUtils.DateToString(time, DateUtils.DateStyle.HH_MM);
            } else if (now.getDate() - time.getDate() < 8) {
                if (now.getDate() - time.getDate() == 1) {
                    return "昨天 " + DateUtils.DateToString(time, DateUtils.DateStyle.HH_MM);
                }
                return DateUtils.getWeek(time).getChineseName() + " " + DateUtils.DateToString(time, DateUtils.DateStyle.HH_MM);
            }
        } else if (now.getYear() == time.getYear()) {
            return DateUtils.DateToString(time, DateUtils.DateStyle.MM_DD_HH_MM);
        }
        return DateUtils.DateToString(time, DateUtils.DateStyle.YYYY_MM_DD);
    }

    /**
     * 聊天时间的显示
     */
    public static String getCorrectTime(Date time) {
        Date now = new Date(); // 当前时间

        if (now.getYear() == time.getYear() && now.getMonth() == time.getMonth()) {
            if (now.getDate() == time.getDate()) {
                return "今天" + DateUtils.DateToString(time, DateUtils.DateStyle.HH_MM);
            } else if (now.getDate() - time.getDate() < 8) {
                if (now.getDate() - time.getDate() == 1) {
                    return "昨天" + DateUtils.DateToString(time, DateUtils.DateStyle.HH_MM);
                } else if (now.getDate() - time.getDate() == 2) {
                    return "前天" + DateUtils.DateToString(time, DateUtils.DateStyle.HH_MM);
                }
                return DateUtils.DateToString(time, DateUtils.DateStyle.MM_DD_HH_MM_EN);
            }

        } else if (now.getYear() == time.getYear()) {
            return DateUtils.DateToString(time, DateUtils.DateStyle.MM_DD_HH_MM_EN);
        }
        return DateUtils.DateToString(time, DateUtils.DateStyle.YYYY_MM_DD_HH_MM_EN);
    }

    public static String getCorrectTime(String date) {
        return getCorrectTime(DateUtils.StringToDate(date));
    }

}
