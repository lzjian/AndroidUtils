package com.lzjian.androidutils.utils;

import android.widget.EditText;

/**
 * @Description: EditText的工具类
 */
public class EditTextUtils {

    public static void canEdit(EditText et){
        et.setFocusableInTouchMode(true);
        et.setFocusable(true);
        et.requestFocus();
    }

    public static void cannotEdit(EditText et){
        et.setFocusable(false);
        et.setFocusableInTouchMode(false);
    }
}
