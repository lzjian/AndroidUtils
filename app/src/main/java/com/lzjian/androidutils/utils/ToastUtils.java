package com.lzjian.androidutils.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.lzjian.androidutils.App;

/**
 * @Description: Toast工具类
 */
public class ToastUtils {

	public static void show(String msg) {
		if(TextUtils.isEmpty(msg)) return;
		Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
	}

	public static void showLong(String msg) {
		if(TextUtils.isEmpty(msg)) return;
		Toast.makeText(App.getInstance(), msg, Toast.LENGTH_LONG).show();
	}
}
