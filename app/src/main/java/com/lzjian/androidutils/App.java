package com.lzjian.androidutils;

import android.app.Application;

/**
 * @Description:
 */

public class App extends Application{

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
