package com.lzjian.androidutils.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.text.TextUtils;

import com.lzjian.androidutils.App;
import com.lzjian.androidutils.AppConfig;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @Description: 图片压缩工具类
 */
public class ImgCompressUtils {

    private static final int DEFAULT_BITMAP_WIDTH = 300;
    private static final int DEFAULT_BITMAP_HEIGHT = 300;

    public static String compressImg(String path, int outWidth, int outHeight) {
        if (TextUtils.isEmpty(path)) return null;
        // 如果输出宽高传进来都是0, 那就给它一个默认值300
        if (outWidth <= 0){
            outWidth = DEFAULT_BITMAP_WIDTH;
        }
        if (outHeight <= 0){
            outHeight = DEFAULT_BITMAP_HEIGHT;
        }
        Bitmap bitmap = scaleDownImg(path, outWidth, outHeight);
        if (bitmap == null) return null;
        try {
            //保存图片
            String imgPath = reduceImgQuality(bitmap, 80);
            if (!bitmap.isRecycled()){
                bitmap.recycle();
                bitmap = null;
                // System.gc()调用一下系统的垃圾回收器进行回收，可以通知垃圾回收器尽快进行回收。
                // 这里需要注意的是，调用System.gc()并不能保证立即开始进行回收过程，而只是为了加快回收的到来。
                System.gc();
            }
            return imgPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @description:按比例缩小图片的像素来达到压缩的目的
     * outWidth是期望输出的图片宽度, outHeight是期望输出的图片高度
     * 图片缩小前后的宽高比是保持不变的
     * 例如缩小前宽200, 高400, 宽高比是0.5,
     * 那么参数outWidth设为100, outHeight100, 而实际上输出宽为50, 高100
     */
    private static Bitmap scaleDownImg(String path, int outWidth, int outHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        // options.inJustDecodeBounds先设为true, 表示不生成bitmap(避免占用过多内存), 只是为了获取bitmap的宽高
        options.inJustDecodeBounds = true;
        // decodeFile后options的outWidth,outHeight就有值了
        BitmapFactory.decodeFile(path, options);
        //根据原始图片的宽高比和期望的输出图片的宽高比计算最终输出的图片的宽和高
        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;
        float srcRatio = srcWidth / srcHeight;
        float outRatio = outWidth / outHeight;
        float actualOutWidth; //最终输出的图片宽度
        float actualOutHeight; //最终输出的图片高度
        if (srcWidth > outWidth || srcHeight > outHeight) {
            if (srcRatio < outRatio) {
                actualOutHeight = outHeight;
                actualOutWidth = actualOutHeight * srcRatio;
            } else if (srcRatio > outRatio) {
                actualOutWidth = outWidth;
                actualOutHeight = actualOutWidth / srcRatio;
            } else {
                actualOutWidth = outWidth;
                actualOutHeight = outHeight;
            }
        }else{
            // 如果要求压缩得到的图片宽和高都比原图的大, 直接把要压缩的图片宽高设为和原图一样
            actualOutWidth = srcWidth;
            actualOutHeight = srcHeight;
        }
        //计算sampleSize
        options.inSampleSize = computeSampleSize(srcWidth, srcHeight, actualOutWidth, actualOutHeight);
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = null; //设为null时, 解码器会自动选取合适的颜色模式来解码
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeFile(path, options);
    }

    private static int computeSampleSize(float srcWidth, float srcHeight, float actualOutWidth, float actualOutHeight) {
        int sampleSize;
        if (srcWidth > actualOutWidth || srcHeight > actualOutHeight) {
            // Math.round四舍五入, 例如math.round(2.2) = 2
            int widthRatio = Math.round(srcWidth / actualOutWidth);
            int heightRatio = Math.round(srcHeight / actualOutHeight);
            sampleSize = Math.min(widthRatio, heightRatio);
        }else{
            sampleSize = 1;
        }
        return sampleSize;
    }

    // 将图片进行降质处理（即降低图片的质量）来达到压缩的目的
    public static String reduceImgQuality(Bitmap bitmap, int quality) throws Exception {
        return reduceImgQuality(bitmap, quality, Bitmap.CompressFormat.JPEG);
    }

    public static String reduceImgQuality(Bitmap bitmap, int quality, Bitmap.CompressFormat format) throws Exception {
        String imgPath = FileUtils.getSaveFolder(AppConfig.IMAGE_DIR).getAbsolutePath()
                + File.separator
                + UUIDUtils.getUUID() + ".jpg";
        File file = new File(imgPath);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file, false));
        bitmap.compress(format, quality, bos);
        bos.flush();
        bos.close();
        ScanFileUtils.scanFile(file);
        return imgPath;
    }

}
