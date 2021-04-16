package com.example.help_screen;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;


public class HelpScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_webview);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.sample;

        Log.d("TAG", "onCreate: "+path);


        Uri uri = Uri.parse(path);

        VideoView videoView = findViewById(R.id.videoView1);

        //Creating MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);


        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();


    }
}