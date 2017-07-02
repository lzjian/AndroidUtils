package com.lzjian.androidutils.utils;

import android.app.Service;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Vibrator;

/**
 * @Description: 发出声音和产生振动的工具类
 */

public class MessageAlertHelper {

    private static MessageAlertHelper instance;

    private static MediaPlayer mediaPlayer;
    private static Vibrator vibrator;

    private static final int VIBRATE_DURATION = 500;

    private MessageAlertHelper(Context context) {
        mediaPlayer = new MediaPlayer();
        vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        try {
            mediaPlayer.setDataSource(context,
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
            mediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized MessageAlertHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (MessageAlertHelper.class) {
                if (instance == null){
                    instance = new MessageAlertHelper(context);
                }
            }
        }
        return instance;
    }

    public void messageAlert(boolean voiceState, boolean vibrateState){
        if (voiceState){
            mediaPlayer.start();
        }
        if (vibrateState){
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }
}
