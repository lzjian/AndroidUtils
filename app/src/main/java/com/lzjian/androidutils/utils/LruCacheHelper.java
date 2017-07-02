package com.lzjian.androidutils.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @Description: LruCache工具类
 */

public class LruCacheHelper {

    private static LruCacheHelper instance;

    private final static int maxMemory = (int) Runtime.getRuntime().maxMemory();

    private final static int cacheSizes = maxMemory / 5;
    //图片缓存
    private static LruCache<String, Bitmap> mLruCache;

    private LruCacheHelper() {
        mLruCache = new LruCache<String, Bitmap>(cacheSizes) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    /**
     * 单例获取该Helper
     */
    public static synchronized LruCacheHelper getInstance() {
        if (instance == null) {
            synchronized (LruCacheHelper .class) {
                if (instance == null){
                    instance = new LruCacheHelper();
                }
            }
        }
        return instance;
    }

    /**
     * @description:从LruCache中获取一张图片，可能会返回null(参数key貌似不能太长)
     */
    public Bitmap getBitmapFormLruCache(String key) {
        return mLruCache.get(key);
    }

    /**
     * @descripion:在LruCache添加一张图片(参数key貌似不能太长)
     */
    public void addBitmapToLruCache(String key, Bitmap bitmap) {
        if (getBitmapFormLruCache(key) == null) {
            if (bitmap != null) {
                mLruCache.put(key, bitmap);
            }
        }
    }
}