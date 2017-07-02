package com.lzjian.androidutils.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.ImageColumns;
import android.text.TextUtils;

import java.io.File;

/**
 * @Description: 文件工具类
 */
public class FileUtils {

    /**
     * @Description: uri转FilePath
     */
    public static String uri2FilePath(Context context, Uri uri) {
        if (context == null) return null;
        if (uri == null) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * @Description: Uri转File
     */
    public static File uri2File(Context context, Uri uri) {
        String path = uri2FilePath(context, uri);
        return TextUtils.isEmpty(path) ? null : new File(path);
    }

    /**
     * @Description: filePath转uri
     */
    public static Uri filePath2Uri(String filePath) {
        return file2Uri(new File(filePath));
    }

    /**
     * @Description: file转uri
     */
    public static Uri file2Uri(File file) {
        return Uri.fromFile(file);
    }

    /**
     * SD卡下创建文件夹
     */
    public static File getSaveFolder(String folderName) {
        File file = new File(getExternalStoragePath() + folderName);
        if (!file.exists()){
            file.mkdirs();
        }else{
            if (file.isFile()){
                file.delete();
                file.mkdirs();
            }
        }
        return file;
    }

    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

}
