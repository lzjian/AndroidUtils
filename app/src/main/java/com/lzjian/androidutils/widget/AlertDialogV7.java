package com.lzjian.androidutils.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Button;

import com.lzjian.androidutils.R;


/**
 * @Description: 封装了V7包的AlertDialog的一些常用方法
 */

public class AlertDialogV7 {

    private Context mContext;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mAlertDialog;

    public AlertDialogV7(Context context){
        mContext = context;
        mBuilder = new AlertDialog.Builder(context);
    }

    public AlertDialogV7 setTitle(String title){
        mBuilder.setTitle(title);
        return this;
    }

    public AlertDialogV7 setMsg(String msg){
        mBuilder.setMessage(msg);
        return this;
    }

    public AlertDialogV7 setPosBtn(String str, DialogInterface.OnClickListener listener){
        mBuilder.setPositiveButton(str, listener);
        return this;
    }

    public AlertDialogV7 setNegBtn(String str, DialogInterface.OnClickListener listener){
        mBuilder.setNegativeButton(str, listener);
        return this;
    }

    public AlertDialogV7 show(){
        mAlertDialog = mBuilder.show();
        return this;
    }

    // 一定要给mAlertDialog赋值才能引用下面这个方法
    public AlertDialogV7 setPosBtnTxtCol(){
        if (mAlertDialog == null) return this;
        Button btn_positive = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        btn_positive.setTextColor(mContext.getResources().getColor(R.color.app_color));
        return this;
    }

    // 一定要给mAlertDialog赋值才能引用下面这个方法
    public AlertDialogV7 setNegBtnTxtCol(){
        if (mAlertDialog == null) return this;
        Button btn_negative = mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        btn_negative.setTextColor(mContext.getResources().getColor(R.color.text_color));
        return this;
    }

    /* example
    new AlertDialogV7(this).setMsg("确定删除这条评论吗?").setPosBtn("确定", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    }).setNegBtn("取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    }).show().setPosBtnTxtCol().setNegBtnTxtCol();
    */
}
