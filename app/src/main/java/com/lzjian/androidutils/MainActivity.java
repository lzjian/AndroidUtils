package com.lzjian.androidutils;

import android.media.MediaScannerConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lzjian.androidutils.utils.FileUtils;
import com.lzjian.androidutils.utils.ImgCompressUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImgCompressUtils.compressImg(FileUtils.getExternalStoragePath()+ File.separator+"IMG_20170225_173819.jpg", 200, 200);
            }
        });
    }
}
