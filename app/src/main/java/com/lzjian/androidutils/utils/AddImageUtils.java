package com.lzjian.androidutils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import com.lzjian.androidutils.AppConfig;

import java.io.File;
import java.io.IOException;

/**
 * @Description: 拍照和从相册获取图片的工具类
 */
public class AddImageUtils {

    public static final int REQUEST_CODE_TAKE_PHOTO = 0x144;// 拍照
    public static final int REQUEST_CODE_ALBUM = 0x245;// 相册
    public static File photoFile; // 照片的文件

    /**
     * @Description: 从相册中获取图片
     */
    public static void fromAlbum(Activity aty) {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_PICK);
        }
        intent.setType("image/*");
        aty.startActivityForResult(intent, REQUEST_CODE_ALBUM);
    }

    /**
     * @Description: 自行拍照
     */
    public static void takePhoto(Activity aty) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = createImagePath(AppConfig.IMAGE_DIR);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile)); // 有这一句intent就会为空
        aty.startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    /**
     * @Description: 自行拍照, imgPath设置图片保存路径
     */
    public static void takePhoto(Activity aty, String imgPath) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = createImagePath(imgPath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile)); // 有这一句intent就会为空
        aty.startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    /**
     * @Description: 创建保存相册的路径
     */
    public static File createImagePath(String imgPath) {
        if (CommonUtils.isExitsSdcard()) {
            File saveDir = FileUtils.getSaveFolder(imgPath);
            File file = new File(saveDir, UUIDUtils.getUUID() + ".png");
            file.delete();
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return file;
        }
        return null;
    }

    /**
     * @Description: 获取照片的图片文件
     */
    public static File getPhotoFile() {
        return photoFile;
    }

}