package com.example.jayde.sdcard_videoview;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    ToggleButton enableMediaController;
    VideoView myVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enableMediaController = (ToggleButton)findViewById(R.id.enableMediaController);
        myVideoView = (VideoView)findViewById(R.id.myvideoview);
        getWindow().setFormat(PixelFormat.UNKNOWN);
        myVideoView.setVideoPath(getViewSrc());
        myVideoView.requestFocus();
        myVideoView.start();

        setMediaController();

        enableMediaController.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                setMediaController();
            }});
    }

    private void setMediaController(){
        if(enableMediaController.isChecked()){
            myVideoView.setMediaController(new MediaController(this));
        }else{
            myVideoView.setMediaController(null);
        }
    }

    private String getViewSrc(){
        File extStorageDirectory = Environment.getExternalStorageDirectory();
        String s = extStorageDirectory.getAbsolutePath() + "/db.mp4";
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        return s;
    }
}