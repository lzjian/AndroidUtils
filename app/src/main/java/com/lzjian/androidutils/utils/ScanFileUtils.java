package com.lzjian.androidutils.utils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.lzjian.androidutils.App;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Description:
 * 当用代码在外部存储生成某个文件或图片时, 在图库里看不到这张图片, 手机连接电脑, 在电脑上会看不到这个文件,
 * 这时有两种方法可以让系统去扫描出这个文件,
 * 1.context.sendBroadcast()
 * 2.MediaScannerConnection.scanFile()
 */

public class ScanFileUtils {

    private static final String TAG = "ScanFileUtils";


    public static void sendBroadcast(File file){
        App.getInstance().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }

    public static void scanFile(File file){
        MediaScannerConnection.scanFile(App.getInstance(), new String[] { file.getAbsolutePath() }, null, null);
    }

    public static void insertImg(File file){
        // 再生成一个文件插入到系统图库(华为荣耀3C是插入到拍照的文件夹里的)
        try {
            MediaStore.Images.Media.insertImage(App.getInstance().getContentResolver(), file.getAbsolutePath(), file.getName(), null);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }
}
