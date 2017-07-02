package com.lzjian.androidutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

/**
 * @Description: 状态栏工具类(设置状态栏颜色)
 */
public class StatusBarUtils {

    // 用在onCreate()方法里的setContentView之后, Android 4.4.2以上改变状态栏颜色,
    // 但是这里KITKAT指的是API19, 包括4.4, 4.4.1, 4.4.2, 4.4.3, 4.4.4
    // 所以当手机版本号是4.4, 4.4.1时可能会出错, 但是模拟器里找不到这两个版本的模拟器, 所以没调试这两个版本的
    public static void setStatusBarColor(Activity aty, int colorId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup decorViewGroup = (ViewGroup) aty.getWindow().getDecorView();
            View statusBarView = new View(aty);
            int statusBarHeight = getStatusBarHeight(aty);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, statusBarHeight);
            params.gravity = Gravity.TOP;
            statusBarView.setLayoutParams(params);
            statusBarView.setBackgroundColor(aty.getResources().getColor(colorId));
            decorViewGroup.addView(statusBarView);

            ViewGroup mContentView = (ViewGroup) aty.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 预留出系统 View 的空间.
                mChildView.setFitsSystemWindows(true);
            }
        }
    }

    private static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
