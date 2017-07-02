package com.lzjian.androidutils.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * @Description: View里的内容生成一个Bitmap
 */

public class ViewToBitmapUtils {

    public static Bitmap getCacheBitmapFromView(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    public static Bitmap getDrawBitmapFromView(View view) {
        Bitmap bitmap = null;
        int viewWidth = view.getMeasuredWidth();
        int viewHeight = view.getMeasuredHeight();
        if (viewWidth > 0 && viewHeight > 0) {
            bitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_8888);
            Canvas cvs = new Canvas(bitmap);
            view.draw(cvs);
        }
        return bitmap;
    }
}
