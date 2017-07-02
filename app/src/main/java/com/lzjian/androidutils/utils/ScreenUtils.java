package com.lzjian.androidutils.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * @Description: 屏幕工具类
 */
public class ScreenUtils {

    public static Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point;
    }
}
